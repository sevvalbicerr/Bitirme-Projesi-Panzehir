package com.example.panzehir.view_Patient.loginsignup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import com.example.panzehir.R
import com.example.panzehir.databinding.SignUpFragmentBinding
import com.example.panzehir.viewModelPatient.loginViewModel.SignUpViewModel

class SignUp : Fragment() {

    private var _binding: SignUpFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: SignUpViewModel by lazy{
        ViewModelProvider(this)[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=SignUpFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signUp_to_login)
        }
    }



}