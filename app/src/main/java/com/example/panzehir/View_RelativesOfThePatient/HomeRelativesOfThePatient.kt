package com.example.panzehir.View_RelativesOfThePatient

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
import com.example.panzehir.databinding.FragmentHomeRelativesOfThePatientBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.HostFragment2
import com.example.panzehir.view_Patient.MainActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.util.*

class HomeRelativesOfThePatient : Fragment() {
    private var _binding: FragmentHomeRelativesOfThePatientBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeRelativesOfThePatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = context?.let { PreferenceManager(it) }!!
        //Medication Tracking
        getMedication()
        binding.seeMoreMedication.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeRelativesOfThePatient_to_addMedicationPage)
        }
        //User Profile
        getUser()
        binding.ProfileLinearLayout.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeRelativesOfThePatient_to_profile_RelativesPatient)}

        //Logout
        binding.signOut.setOnClickListener {
            signOut()
        }
        //Switch Account
        binding.switchAccount.setOnClickListener {
            val intent=Intent(this.context,HostFragment2::class.java)
            startActivity(intent)
        }
        //Water tracking
        getWater()
         var glassOfWater=0
        binding.wateradd.setOnClickListener{
            if (binding.waterTextview.text!=""){
                glassOfWater=Integer.parseInt(binding.waterTextview.text.toString())
                glassOfWater++
            }
            binding.waterTextview.text=(glassOfWater).toString()
            recordWater()
        }
        binding.waterReduce.setOnClickListener {
            if (binding.waterTextview.text!="" && glassOfWater!=0){
                glassOfWater=Integer.parseInt(binding.waterTextview.text.toString())
                glassOfWater--
            }
            binding.waterTextview.text=(glassOfWater).toString()
            recordWater()
        }
        //Step Counter
        loadDataStep()



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
    fun recordWater(){
        val glassOfWater: HashMap<String, Any> = HashMap()
        val tc=preferenceManager.getString(Constants.KEY_ID_PATIENT)!!
        glassOfWater[ConstantsForRelativesMedication.KEY_WATER] = binding.waterTextview.text.toString()
        glassOfWater[Constants.KEY_ID_PATIENT]=tc
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_WATER)
            .document(tc)
            .set(glassOfWater)
            .addOnSuccessListener {
               Toast.makeText(context, "Su takibi güncellendi: ", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { Toast.makeText(context, "Error: " + it.message, Toast.LENGTH_SHORT).show() }
    }
    @SuppressLint("SetTextI18n")
    fun getWater(){
        val myUserId = preferenceManager.getString(Constants.KEY_ID_PATIENT)
        println("myuserİD ${myUserId}")
        val database = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_WATER)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null ) { println("completelistener ifffffffffff")
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        if(myUserId==documentSnapshot.getString(Constants.KEY_ID_PATIENT).toString()){
                            binding.waterTextview.text=documentSnapshot.getString(ConstantsForRelativesMedication.KEY_WATER)!!.toString()
                        }
                    }
                }
            }
    }
    @SuppressLint("SetTextI18n")
    private fun getUser(){
        binding.nameOfPerson.text=preferenceManager.getString(Constants.KEY_FIRST_NAME_PATIENT)+" "+preferenceManager.getString(
            Constants.KEY_LAST_NAME_PATIENT)
        binding.typeOfBlood.text=preferenceManager.getString(Constants.KEY_BLOOD_PATIENT)
        val gender=preferenceManager.getString(Constants.KEY_GENDER_PATIENT)
        if (gender=="Erkek"){
            binding.photo.setBackgroundResource(R.drawable.dede)
        }
        else {
            binding.photo.setBackgroundResource(R.drawable.me)
        }
        val now = Calendar.getInstance()
        val nowYear=now.get(Calendar.YEAR)
//        println("nowYear  "+ nowYear)
        //birthday kullanıcı bilgisi boş geliyor.
        //val age=nowYear-preferenceManager.getString(Constants.KEY_BIRTHDAY_PATIENT)!!.toInt()
        //binding.typeOfBlood.text=age
    }
    @SuppressLint("SetTextI18n")
    fun getMedication(){
        val myUserId = preferenceManager.getString(ConstantsForRelativesMedication.KEY_PATIENT_ID)
        var i =0
        val database = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_MEDICATION)
            .whereEqualTo(ConstantsForRelativesMedication.KEY_PATIENT_ID,myUserId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null) {
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        if (i==0){

                            binding.medicaiton1.text= documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()+" "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()+"\'da "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()+"tane "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                            i++
                        }
                        else if(i==1){
                            binding.medicaiton2.text= documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()+" "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()+"\'da "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()+"tane "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                            i++
                        }
                        else{
                            binding.medicaiton3.text= documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()+" "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()+"\'da "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()+"tane "+
                                    documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                        }


                    }
                }
            }
    }
    private fun loadDataStep() {
        val myUserId = preferenceManager.getString(Constants.KEY_ID_PATIENT)
        val database = FirebaseFirestore.getInstance()
        database.collection("Step_Counter")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful && it.result != null ) {
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        if(myUserId==documentSnapshot.getString("patient_id").toString()){
                            binding.NumberOfStepText.text=documentSnapshot.getString("step")!!.toString()

                        }
                    }
                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}