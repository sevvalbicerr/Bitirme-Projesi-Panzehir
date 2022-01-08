package com.example.panzehir.view_Patient

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.HomeFragmentBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.viewModelPatient.HomeViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.time.LocalDate
import java.util.*
import kotlin.math.sign

class Home : Fragment() {
    private var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager

    private  val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = context?.let { PreferenceManager(it) }!!

        // Navigation
        binding.ProfileLinearLayout.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_home2_to_profile) }
        binding.seeMoreMemories.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_home2_to_memories2) }
        binding.seeMoreMedication.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_home2_to_medicationTracking) }
        binding.signOut.setOnClickListener{
            signOut()
        }
        binding.callEmergencyLayout.setOnClickListener { emergencyAlertButton() }
        binding.callFamilyLayout.setOnClickListener { callMyFamilyAlertButton(it) }
        getMedication()
        getUser()
    }
    @SuppressLint("SetTextI18n")
    fun getMedication(){
        val myUserId = preferenceManager.getString(ConstantsForRelativesMedication.KEY_PATIENT_ID)
        println(myUserId+":+++++ userId")
        var i =0
        val database = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_MEDICATION)
            .whereEqualTo(ConstantsForRelativesMedication.KEY_PATIENT_ID,myUserId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null) {
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        if (i==0){

                            binding.medication1.text= documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()+" "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()+"\'da "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()+"tane "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                            i++
                        }
                        else if(i==1){
                            binding.medication2.text= documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()+" "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()+"\'da "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()+"tane "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                            i++
                        }
                        else{
                            binding.medication3.text= documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()+" "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()+"\'da "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()+"tane "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                        }


                    }
                }
            }
    }
    private fun signOut() {
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
    @SuppressLint("SetTextI18n")
    private fun getUser(){
        binding.nameOfPerson.text=preferenceManager.getString(Constants.KEY_FIRST_NAME_PATIENT)+" "+preferenceManager.getString(Constants.KEY_LAST_NAME_PATIENT)
        binding.typeOfBlood.text=preferenceManager.getString(Constants.KEY_BLOOD_PATIENT)
        val now =Calendar.getInstance()
        val nowYear=now.get(Calendar.YEAR)
//        println("nowYear  "+ nowYear)
        //birthday kullanıcı bilgisi boş geliyor.
        //val age=nowYear-preferenceManager.getString(Constants.KEY_BIRTHDAY_PATIENT)!!.toInt()
        //binding.typeOfBlood.text=age



    }
    private fun callMyFamilyAlertButton(view: View) { Navigation.findNavController(view).navigate(R.id.action_home2_to_friendList) }

    fun emergencyAlertButton(){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Arama")
        builder.setMessage("Acil Durum Hattını Aramak İstiyor Musunuz?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Ara") { dialog, which ->
            Toast.makeText(this.context,
                "Aranılıyor...", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Arama") { dialog, which ->
            Toast.makeText(this.context,
                "Vazgeçildi.", Toast.LENGTH_SHORT).show()
        }
        builder.show()


    }
}