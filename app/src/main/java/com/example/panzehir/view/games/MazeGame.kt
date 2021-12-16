@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view.games

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.MazeGameFragmentBinding
import com.example.panzehir.viewModel.gamesViewModel.MazeGameViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class MazeGame : Fragment() {

    private var _binding: MazeGameFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: MazeGameViewModel by lazy{
        ViewModelProvider(this)[MazeGameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=MazeGameFragmentBinding.inflate(inflater,container,false)
        return binding.root

        // bottom_nav_menu_patient added 2 more items to improve the appearance
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.menu.getItem(2).isEnabled = false
    }



}