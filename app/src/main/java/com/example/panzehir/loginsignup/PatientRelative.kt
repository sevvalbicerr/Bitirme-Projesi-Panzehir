package com.example.panzehir.loginsignup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.panzehir.View_RelativesOfThePatient.RelativesOfThePatientMainActivity
import com.example.panzehir.databinding.PatientRelativeFragmentBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.google.firebase.firestore.FirebaseFirestore

class PatientRelative : Fragment() {

    private var _binding: PatientRelativeFragmentBinding?=null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=PatientRelativeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = context?.let { PreferenceManager(it) }!!

        binding.loginButtonInPatientRelatives.setOnClickListener{
            if(binding.inputPasswordRelative.text.toString().trim().isEmpty()){
                Toast.makeText(context, "Parolanızı giriniz", Toast.LENGTH_SHORT).show()
            } else{
                signInRelative()
            }
        }

    }

    private fun signInRelative() {
        binding.loginButtonInPatientRelatives.visibility = View.INVISIBLE
        binding.loginRelativeProgressBar.visibility = View.VISIBLE
        val myUserId = preferenceManager.getString(Constants.KEY_ID_PATIENT)
        val database = FirebaseFirestore.getInstance()
        database.collection(Constants.KEY_COLLECTION_USERS)
            .whereEqualTo(Constants.KEY_ID_PATIENT,myUserId)
            .get()
            .addOnCompleteListener {
                it.let {
                    if (it.isSuccessful && it.result != null && it.result!!.documents.size > 0){
                        val documentSnapshot = it.result!!.documents[0]
                        if(binding.inputPasswordRelative.text.toString()==documentSnapshot.getString(Constants.KEY_PASSWORD_ACCOUNT)!!){
                            //preferenceManager.putString(Constants.KEY_PASSWORD_ACCOUNT,documentSnapshot.getString(Constants.KEY_PASSWORD_ACCOUNT)!!)
                            val intent= Intent(activity, RelativesOfThePatientMainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }
                        else{
                            binding.loginRelativeProgressBar.visibility = View.INVISIBLE
                            binding.loginButtonInPatientRelatives.visibility = View.VISIBLE
                            Toast.makeText(context, "Girilen parola yanlıştır. Lütfen tekrar deneyiniz", Toast.LENGTH_SHORT).show()
                        }


                    }
                }
            }
    }


}