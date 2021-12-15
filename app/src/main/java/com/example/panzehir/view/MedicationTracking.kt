package com.example.panzehir.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.databinding.HomeFragmentBinding
import com.example.panzehir.databinding.MedicationTrackingFragmentBinding
import com.example.panzehir.viewModel.MedicationTrackingViewModel

class MedicationTracking : Fragment() {

    private var _binding: MedicationTrackingFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: MedicationTrackingViewModel by lazy{
        ViewModelProvider(this)[MedicationTrackingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=MedicationTrackingFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }



}