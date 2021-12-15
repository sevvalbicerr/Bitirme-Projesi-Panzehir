@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.panzehir.databinding.SudokuFragmentBinding
import com.example.panzehir.viewModel.gamesViewModel.SudokuViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class Sudoku : Fragment() {

    private var _binding: SudokuFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: SudokuViewModel by lazy {
        ViewModelProvider(this)[SudokuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=SudokuFragmentBinding.inflate(inflater,container,false)
        return binding.root

        // bottom_nav_menu_patient added 2 more items to improve the appearance
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.menu.getItem(2).isEnabled = false
    }


}