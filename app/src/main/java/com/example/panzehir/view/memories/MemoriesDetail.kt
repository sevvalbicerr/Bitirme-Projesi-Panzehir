package com.example.panzehir.view.memories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.databinding.MedicationTrackingFragmentBinding
import com.example.panzehir.databinding.MemoriesDetailFragmentBinding
import com.example.panzehir.viewModel.memoriesViewModel.MemoriesDetailViewModel

class MemoriesDetail : Fragment() {


    private var _binding: MemoriesDetailFragmentBinding?=null
    private val binding get() = _binding!!
    private  val viewModel: MemoriesDetailViewModel by lazy{
        ViewModelProvider(this)[MemoriesDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=MemoriesDetailFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


}