@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view_Patient.games

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.GamesFragmentBinding
import com.example.panzehir.viewModelPatient.gamesViewModel.GamesViewModel

class Games : Fragment() {

    private var _binding: GamesFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: GamesViewModel by lazy{
        ViewModelProvider(this)[GamesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=GamesFragmentBinding.inflate(inflater,container,false)
        return binding.root

        // bottom_nav_menu_patient added 2 more items to improve the appearance
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }


}