package com.example.panzehir.viewPatient.loginsignup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.PatientRelativeFragmentBinding
import com.example.panzehir.viewModelPatient.loginViewModel.PatientRelativeViewModel

class PatientRelative : Fragment() {
    private var _binding: PatientRelativeFragmentBinding?=null
    private val binding get() = _binding!!
    private  val viewModel: PatientRelativeViewModel by lazy {
        ViewModelProvider(this)[PatientRelativeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=PatientRelativeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }



}