@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.ProfileFragmentBinding
import com.example.panzehir.viewModel.ProfileViewModel
class Profile : Fragment() {
    private var _binding: ProfileFragmentBinding?=null
    private val binding get() = _binding!!
    private  val viewModel: ProfileViewModel by lazy{
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=ProfileFragmentBinding.inflate(inflater,container,false)
        return binding.root
        // bottom_nav_menu_patient added 2 more items to improve the appearance
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}