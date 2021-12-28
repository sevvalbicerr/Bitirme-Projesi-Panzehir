package com.example.panzehir.loginsignup

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentLoginBinding
import com.example.panzehir.viewModelPatient.loginViewModel.LoginViewModel
import com.example.panzehir.view_Patient.HostFragment2

class Login : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.LoginButton.setOnClickListener {
            loginChoose(it)
        }
        binding.SignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_signUp)
        }
        binding.ForgotPasswordInLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_forgotPassword)
        }

    }
    private fun loginChoose(view: View){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Giriş Yap")
        builder.setMessage("Giriş Tipini Seçiniz!")
        builder.setPositiveButton("Hasta Girişi") { dialog, which ->
            Toast.makeText(this.context,
                "Hasta Girişi Yapılıyor...", Toast.LENGTH_SHORT).show()
             val intent= Intent(context,HostFragment2::class.java)
           startActivity(intent)
        }
        builder.setNegativeButton("Hasta Yakını Girişi") { dialog, which ->
            Toast.makeText(this.context,
                "Hasta Yakını Girişi .", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_login_to_patientRelative)
        }
        builder.show()


    }

}