@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.viewPatient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.HomeFragmentBinding
import com.example.panzehir.viewModelPatient.HomeViewModel

class Home : Fragment() {
    private var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!
    private  val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ProfileLinearLayout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home2_to_profile)
        }
        binding.seeMoreMemories.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home2_to_memories2)
        }
        binding.seeMoreMedication.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home2_to_medicationTracking)
        }
        // bottom_nav_menu_patient added 2 more items to improve the appearance
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}