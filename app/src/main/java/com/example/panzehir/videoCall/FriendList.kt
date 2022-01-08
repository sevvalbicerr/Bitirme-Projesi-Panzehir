package com.example.panzehir.videoCall

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.adapters.UsersAdapter
import com.example.panzehir.databinding.FragmentFriendListBinding
import com.example.panzehir.listeners.UsersListener
import com.example.panzehir.model.User
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.HostFragment2
import com.example.panzehir.view_Patient.MainActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.messaging.FirebaseMessaging

class FriendList : Fragment(), UsersListener {

    private var _binding: FragmentFriendListBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager
    private var users = ArrayList<User>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentFriendListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = context?.let { PreferenceManager(it) }!!


        // Navigation
        binding.backFriendList.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_friendList_to_home2)
        }
        binding.textSignOut.setOnClickListener { signOut(it) }

        // Send FCM token database
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                sendFCMTokenToDatabase(task.result)
            }
        }

        //Fragment is attached to a RecyclerView
        usersAdapter = UsersAdapter(users,this)
        binding.usersRecyclerView.adapter = usersAdapter

        binding.swipeRefreshLayout.setOnRefreshListener(this::getUser)

        getUser()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUser() {
        binding.swipeRefreshLayout.isRefreshing = true

        val database = FirebaseFirestore.getInstance()
        database.collection(Constants.KEY_COLLECTION_USERS)
            .get()
            .addOnCompleteListener {
                binding.swipeRefreshLayout.isRefreshing = false
                val myUserId = preferenceManager.getString(Constants.KEY_ID_PATIENT)
                if (it.isSuccessful && it.result != null) {
                    // Using swipe refresh layout, it can be called multiple times that's why I need to clear user list before adding new data
                    users.clear()
                    // Here, we will display the user list except for the currently signed-in user,
                    // because no one will have a meeting with himself. That's why we are excluding a signed-in user from the list
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        if (myUserId.equals(documentSnapshot.id)) {
                            continue
                        }
                        val user = User()
                        user.firstNamePatient = documentSnapshot.getString(Constants.KEY_FIRST_NAME_PATIENT).toString()
                        user.lastNamePatient = documentSnapshot.getString(Constants.KEY_LAST_NAME_PATIENT).toString()
                        user.email = documentSnapshot.getString(Constants.KEY_EMAIL).toString()
                        user.token = documentSnapshot.getString(Constants.KEY_FCM_TOKEN).toString()
                        users.add(user)
                    }
                    if (users.size > 0) {
                        usersAdapter.notifyDataSetChanged()
                    } else {
                        binding.textErrorMessage.text = String.format("%s ", "Kayıtlı kullanıcı bulunamadı")
                        binding.textErrorMessage.visibility = View.VISIBLE
                    }
                } else {
                    binding.textErrorMessage.text = String.format("%s ", "Kayıtlı kullanıcı bulunamadı")
                    binding.textErrorMessage.visibility = View.VISIBLE
                }
            }

    }

    private fun sendFCMTokenToDatabase(token: String) {
        val database = FirebaseFirestore.getInstance()
        val documentReference = database.collection(Constants.KEY_COLLECTION_USERS)
            .document(preferenceManager.getString(Constants.KEY_ID_PATIENT)!!)

        documentReference.update(Constants.KEY_FCM_TOKEN, token)
            .addOnFailureListener { Toast.makeText(context, "Unable to send token: " + it.message, Toast.LENGTH_SHORT).show() }
    }

    private fun signOut(view: View) {
        Toast.makeText(context, "Çıkış yapılıyor", Toast.LENGTH_SHORT).show()
        val database = FirebaseFirestore.getInstance()
        val documentReference = database.collection(Constants.KEY_COLLECTION_USERS)
            .document(preferenceManager.getString(Constants.KEY_ID_PATIENT)!!)
        val updates: HashMap<String, Any> = HashMap()
        updates[Constants.KEY_FCM_TOKEN] = FieldValue.delete()
        documentReference.update(updates)
            .addOnSuccessListener {
                preferenceManager.clearPreference()
                val intent= Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Çıkış yapılamadı", Toast.LENGTH_SHORT).show()
            }
    }

    override fun initiateVideoMeeting(user: User) {
        if (user.token == "null" || user.token.trim().isEmpty() ){
            Toast.makeText(context, user.firstNamePatient + " " + user.lastNamePatient + " kişisi görüntülü görüşme için uygun değil", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(activity!!.applicationContext, OutgoingInvitationActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("type","video")
            startActivity(intent)
        }
    }

    override fun initiateAudioMeeting(user: User) {
        if (user.token == "null" || user.token.trim().isEmpty() ){
            Toast.makeText(context, user.firstNamePatient + " " + user.lastNamePatient + " kişisi sesli görüşme için uygun değil", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(activity!!.applicationContext, OutgoingInvitationActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("type","audio")
            startActivity(intent)
        }
    }

}