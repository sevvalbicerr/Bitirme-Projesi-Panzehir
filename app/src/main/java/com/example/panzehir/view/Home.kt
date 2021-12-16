@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.HomeFragmentBinding
import com.example.panzehir.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

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

        // bottom_nav_menu_patient added 2 more items to improve the appearance
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}