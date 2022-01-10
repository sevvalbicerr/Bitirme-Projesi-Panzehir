package com.example.panzehir.view_Patient.games.mazegame

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.databinding.MazeGameFragmentBinding
import com.example.panzehir.viewModelPatient.gamesViewModel.MazeGameViewModel

class Maze : Fragment(){

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.NewGamebutton.setOnClickListener {

        }
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Oyuna başla")
        builder.setPositiveButton("Başla") { dialog, which ->
            builder.setCancelable(true)
            binding.chronomether.start()
        }
        builder.show()
        println(binding.chronomether.text)


    }




}