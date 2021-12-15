package com.example.panzehir.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentLoginBinding
import com.example.panzehir.databinding.HomeFragmentBinding
import com.example.panzehir.viewModel.HomeViewModel

class Home : Fragment() {
    private var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!
    private  val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}