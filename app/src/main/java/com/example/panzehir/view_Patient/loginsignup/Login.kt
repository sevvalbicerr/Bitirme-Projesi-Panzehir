package com.example.panzehir.view_Patient.loginsignup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentLoginBinding
import com.example.panzehir.viewModelPatient.loginViewModel.LoginViewModel

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
            Navigation.findNavController(it).navigate(R.id.action_login_to_home2)
        }
        binding.SignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_signUp)
        }
        binding.ForgotPasswordInLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_forgotPassword)
        }

    }

}