@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view_Patient.games

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.MazeGameFragmentBinding
import com.example.panzehir.viewModelPatient.gamesViewModel.MazeGameViewModel

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

    }



}