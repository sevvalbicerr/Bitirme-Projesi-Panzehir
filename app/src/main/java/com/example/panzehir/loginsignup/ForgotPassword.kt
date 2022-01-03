package com.example.panzehir.loginsignup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.panzehir.databinding.FragmentForgotPasswordBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.MainActivity
import com.google.firebase.firestore.FirebaseFirestore


class ForgotPassword : Fragment() {

    private var _binding: FragmentForgotPasswordBinding?=null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding= FragmentForgotPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = context?.let { PreferenceManager(it) }!!

        binding.forgetButton.setOnClickListener {
            if (binding.inputEmailForget.text.toString().trim().isEmpty()){
                Toast.makeText(context, "E-posta adresinizi giriniz", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmailForget.text.toString()).matches()){
                Toast.makeText(context, "Geçerli e-posta adresi giriniz", Toast.LENGTH_SHORT).show()
            }else{
                sendEmail()
            }
        }

    }

    private fun sendEmail() {
        binding.forgetButton.visibility = View.INVISIBLE
        binding.forgetProgressBar.visibility = View.VISIBLE

        val database = FirebaseFirestore.getInstance()
        database.collection(Constants.KEY_COLLECTION_USERS)
            .whereEqualTo(Constants.KEY_EMAIL,binding.inputEmailForget.text.toString())
            .get()
            .addOnCompleteListener {
                it.let {
                    if (it.isSuccessful && it.result != null && it.result!!.documents.size > 0){
                        val documentSnapshot = it.result!!.documents[0]
                        preferenceManager.putString(Constants.KEY_EMAIL,documentSnapshot.getString(Constants.KEY_EMAIL)!!)
                        Toast.makeText(context, "Sıfırlama maili gönderildi", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }else{
                        binding.forgetProgressBar.visibility = View.INVISIBLE
                        binding.forgetButton.visibility = View.VISIBLE
                        Toast.makeText(context, "Girilen e-posta ile kayıtlı hesap bulunamadı", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}