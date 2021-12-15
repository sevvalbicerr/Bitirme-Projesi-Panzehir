package com.example.panzehir.view.memories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.panzehir.R
import com.example.panzehir.databinding.MedicationTrackingFragmentBinding
import com.example.panzehir.databinding.MemoriesFragmentBinding
import com.example.panzehir.viewModel.memoriesViewModel.MemoriesViewModel

class Memories : Fragment() {


    private var _binding: MemoriesFragmentBinding?=null
    private val binding get() = _binding!!
    private  val viewModel: MemoriesViewModel by lazy {
        ViewModelProvider(this)[MemoriesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=MemoriesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }



}