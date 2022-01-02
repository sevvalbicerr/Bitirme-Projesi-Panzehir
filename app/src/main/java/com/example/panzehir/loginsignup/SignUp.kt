package com.example.panzehir.loginsignup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.SignUpFragmentBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.MainActivity
import com.google.firebase.firestore.FirebaseFirestore

class SignUp : Fragment() {

    private var _binding: SignUpFragmentBinding?=null
    private val binding get() = _binding!!

    private lateinit var selectedBlood: String
    private lateinit var selectedGender: String
    private lateinit var selectedDegree: String

    private lateinit var preferenceManager: PreferenceManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=SignUpFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = context?.let { PreferenceManager(it) }!!

        // Navigation
        binding.alreadyHave.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_signUp_to_login) }
        binding.signUpButton.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_signUp_to_login) }

        // Spinner
        binding.bloodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { selectedBlood = parent?.getItemAtPosition(position).toString() }
            override fun onNothingSelected(parent: AdapterView<*>?) { selectedBlood = "None" }
        }
        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { selectedGender = parent?.getItemAtPosition(position).toString() }
            override fun onNothingSelected(parent: AdapterView<*>?) { selectedGender = "None" }
        }
        binding.degreeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { selectedDegree = parent?.getItemAtPosition(position).toString() }
            override fun onNothingSelected(parent: AdapterView<*>?) { selectedDegree = "None" }
        }


        binding.signUpButton.setOnClickListener {
            if (binding.nameofPatientEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hastanın ismini giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.surnameofPatientEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hastanın soy ismini giriniz", Toast.LENGTH_SHORT).show()
            } else if (selectedBlood == "None"){
                Toast.makeText(context, "Hastanın kan grubunu giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.TCEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hastanın TC kimlik numarasını giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.TCEdittext.length() != 11) {
                Toast.makeText(context, "TC kimlik numarası 11 haneli olmalıdır", Toast.LENGTH_SHORT).show()
            } else if (binding.TCEdittext.toString().startsWith("0")) {
                Toast.makeText(context, "TC kimlik numarası 0 ile başlayamaz", Toast.LENGTH_SHORT).show()
            } else if (binding.weightEdittext.text.toString().trim().isEmpty()){
                Toast.makeText(context, "Hastanın ağırlığını giriniz", Toast.LENGTH_SHORT).show()
            }else if (binding.heightEdittext.text.toString().trim().isEmpty()){
                Toast.makeText(context, "Hastanın boyunu giriniz", Toast.LENGTH_SHORT).show()
            } else if (selectedGender == "None"){
                Toast.makeText(context, "Hastanın cinsiyetini giriniz", Toast.LENGTH_SHORT).show()
            }else if (binding.AdressEdittext.text.toString().trim().isEmpty()){
                Toast.makeText(context, "Hastanın adresini giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.nameofRelatevesEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hasta yakınının ismini giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.surnameofRelativesEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hasta yakınının soy ismini giriniz", Toast.LENGTH_SHORT).show()
            } else if (selectedDegree == "None"){
                Toast.makeText(context, "Hasta yakınının derecesini seçiniz", Toast.LENGTH_SHORT).show()
            } else if (binding.emailEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hasta yakınının email adresini giriniz", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEdittext.text.toString()).matches()) {
                Toast.makeText(context, "Hasta yakınının email adresini tekrar giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Parolanızı giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordAgainEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Parolanızın tekrar giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordEdittext.text.toString() != binding.passwordAgainEdittext.text.toString()) {
                Toast.makeText(context, "Parolanız ile doğrulama parolanız aynı olmalıdır", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordAccountEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Oturum parolanızı giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordAccountAgain.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Oturum parolanızın tekrar giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.passwordAccountEdittext.text.toString() != binding.passwordAccountAgainEdittext.text.toString()) {
                Toast.makeText(context, "Oturum parolanız ile doğrulama oturum parolanız aynı olmalıdır", Toast.LENGTH_SHORT).show()
            }  else if (binding.numberEdittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hasta yakınının telefon numarasını giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.numberEdittext.length() != 11) {
                Toast.makeText(context, "Hasta yakınının telefon numarası 11 haneli olmalıdır", Toast.LENGTH_SHORT).show()
            } else if (binding.number2Edittext.text.toString().trim().isEmpty()) {
                Toast.makeText(context, "Hasta yakınının ikinci telefon numarasını giriniz", Toast.LENGTH_SHORT).show()
            } else if (binding.number2Edittext.length() != 11) {
                Toast.makeText(context, "Hasta yakınının ikinci telefon numarası 11 haneli olmalıdır", Toast.LENGTH_SHORT).show()
            } else {
                signUp()
            }
        }

    }


    private fun signUp(){
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        val user: HashMap<String, Any> = HashMap()

        // Patient Information
        user[Constants.KEY_FIRST_NAME_PATIENT] = binding.nameofPatientEdittext.text.toString()
        user[Constants.KEY_LAST_NAME_PATIENT] = binding.surnameofPatientEdittext.text.toString()
        user[Constants.KEY_BLOOD_PATIENT] = selectedBlood
        user[Constants.KEY_ID_PATIENT] = binding.TCEdittext.text.toString()
        user[Constants.KEY_WEIGHT_PATIENT] = binding.weightEdittext.text.toString()
        user[Constants.KEY_HEIGHT_PATIENT] = binding.heightEdittext.text.toString()
        user[Constants.KEY_GENDER_PATIENT] = selectedGender
        user[Constants.KEY_ADDRESS_PATIENT] = binding.AdressEdittext.text.toString()
        // Patient Relative Information
        user[Constants.KEY_FIRST_NAME_RELATIVE_PATIENT] = binding.nameofRelatevesEdittext.text.toString()
        user[Constants.KEY_LAST_NAME_RELATIVE_PATIENT] = binding.surnameofRelativesEdittext.text.toString()
        user[Constants.KEY_DEGREE] = selectedDegree
        user[Constants.KEY_EMAIL] = binding.emailEdittext.text.toString()
        user[Constants.KEY_PASSWORD] = binding.passwordEdittext.text.toString()
        user[Constants.KEY_PASSWORD_ACCOUNT] = binding.passwordAccountEdittext.text.toString()
        user[Constants.KEY_PHONE1] = binding.numberEdittext.text.toString()
        user[Constants.KEY_PHONE2] = binding.number2Edittext.text.toString()


        database.collection(Constants.KEY_COLLECTION_USERS)
            .document(binding.TCEdittext.text.toString())
            .set(user)
            .addOnSuccessListener {
                preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                // Patient Information
                preferenceManager.putString(Constants.KEY_FIRST_NAME_PATIENT,binding.nameofPatientEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_LAST_NAME_PATIENT,binding.surnameofPatientEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_BLOOD_PATIENT,selectedBlood)
                preferenceManager.putString(Constants.KEY_ID_PATIENT,binding.TCEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_WEIGHT_PATIENT,binding.weightEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_HEIGHT_PATIENT,binding.heightEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_GENDER_PATIENT,selectedGender)
                preferenceManager.putString(Constants.KEY_ADDRESS_PATIENT,binding.AdressEdittext.text.toString())
                // Patient Relative Information
                preferenceManager.putString(Constants.KEY_FIRST_NAME_RELATIVE_PATIENT,binding.nameofRelatevesEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_LAST_NAME_RELATIVE_PATIENT,binding.surnameofRelativesEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_DEGREE,selectedDegree)
                preferenceManager.putString(Constants.KEY_EMAIL,binding.emailEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_PHONE1,binding.numberEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_PHONE2,binding.number2Edittext.text.toString())

                val intent = Intent(activity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener { Toast.makeText(context, "Error: " + it.message, Toast.LENGTH_SHORT).show() }

    }

}