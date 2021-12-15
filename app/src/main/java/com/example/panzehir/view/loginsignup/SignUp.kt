package com.example.panzehir.view.loginsignup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.databinding.MedicationTrackingFragmentBinding
import com.example.panzehir.databinding.SignUpFragmentBinding
import com.example.panzehir.viewModel.loginViewModel.SignUpViewModel

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



}