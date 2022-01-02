package com.example.panzehir.loginsignup

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentLoginBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.HostFragment2
import com.google.firebase.firestore.FirebaseFirestore


class Login : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = context?.let { PreferenceManager(it) }!!

        // The account will remain open until the sign-out button is clicked
        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)){
            val intent = Intent(activity, HostFragment2::class.java)
            startActivity(intent)
            activity!!.finish()
        }

        // Navigation
        binding.SignUp.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_login_to_signUp) }
        binding.ForgotPasswordInLogin.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_login_to_forgotPassword) }


        binding.loginButton.setOnClickListener {
            if (binding.inputEmail.text.toString().trim().isEmpty()){
                Toast.makeText(context, "E-posta adresinizi giriniz", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches()){
                Toast.makeText(context, "Geçerli e-posta adresi giriniz", Toast.LENGTH_SHORT).show()
            } else if(binding.inputPassword.text.toString().trim().isEmpty()){
                Toast.makeText(context, "Parolanızı giriniz", Toast.LENGTH_SHORT).show()
            } else {
                signIn(it)
            }
        }



    }

    private fun signIn(view: View) {
        binding.loginButton.visibility = View.INVISIBLE
        binding.signInProgressBar.visibility = View.VISIBLE

        val database = FirebaseFirestore.getInstance()
        database.collection(Constants.KEY_COLLECTION_USERS)
            .whereEqualTo(Constants.KEY_EMAIL,binding.inputEmail.text.toString())
            .whereEqualTo(Constants.KEY_PASSWORD,binding.inputPassword.text.toString())
            .get()
            .addOnCompleteListener {
                it.let {
                    if (it.isSuccessful && it.result != null && it.result!!.documents.size > 0){
                        val documentSnapshot = it.result!!.documents[0]
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                        // Patient Information
                        preferenceManager.putString(Constants.KEY_FIRST_NAME_PATIENT,documentSnapshot.getString(Constants.KEY_FIRST_NAME_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_LAST_NAME_PATIENT,documentSnapshot.getString(Constants.KEY_LAST_NAME_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_BLOOD_PATIENT,documentSnapshot.getString(Constants.KEY_BLOOD_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_ID_PATIENT,documentSnapshot.getString(Constants.KEY_ID_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_WEIGHT_PATIENT,documentSnapshot.getString(Constants.KEY_WEIGHT_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_HEIGHT_PATIENT,documentSnapshot.getString(Constants.KEY_HEIGHT_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_GENDER_PATIENT,documentSnapshot.getString(Constants.KEY_GENDER_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_ADDRESS_PATIENT,documentSnapshot.getString(Constants.KEY_ADDRESS_PATIENT)!!)
                        // Patient Relative Information
                        preferenceManager.putString(Constants.KEY_FIRST_NAME_RELATIVE_PATIENT,documentSnapshot.getString(Constants.KEY_FIRST_NAME_RELATIVE_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_LAST_NAME_RELATIVE_PATIENT,documentSnapshot.getString(Constants.KEY_LAST_NAME_RELATIVE_PATIENT)!!)
                        preferenceManager.putString(Constants.KEY_DEGREE,documentSnapshot.getString(Constants.KEY_DEGREE)!!)
                        preferenceManager.putString(Constants.KEY_EMAIL,documentSnapshot.getString(Constants.KEY_EMAIL)!!)
                        preferenceManager.putString(Constants.KEY_PHONE1,documentSnapshot.getString(Constants.KEY_PHONE1)!!)
                        preferenceManager.putString(Constants.KEY_PHONE2,documentSnapshot.getString(Constants.KEY_PHONE2)!!)

                        val builder = AlertDialog.Builder(this.context)
                        builder.setTitle("Giriş Yap")
                        builder.setMessage("Giriş Tipini Seçiniz!")
                        builder.setPositiveButton("Hasta Girişi") { _, _ ->
                            Toast.makeText(this.context, "Hasta Girişi Yapılıyor...", Toast.LENGTH_SHORT).show()
                            val intent= Intent(context,HostFragment2::class.java)
                            startActivity(intent)
                        }
                        builder.setNegativeButton("Hasta Yakını Girişi") { _, _ ->
                            Toast.makeText(this.context, "Hasta Yakını Giriş Sayfasına Yönlendiriliyorsunuz. ", Toast.LENGTH_SHORT).show()
                            Navigation.findNavController(view).navigate(R.id.action_login_to_patientRelative)
                        }
                        builder.show()

                    }else{
                        binding.signInProgressBar.visibility = View.INVISIBLE
                        binding.loginButton.visibility = View.VISIBLE
                        Toast.makeText(context, "Giriş Yapılamadı", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}