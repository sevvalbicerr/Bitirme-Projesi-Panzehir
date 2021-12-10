package com.example.panzehir.view.games

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.viewModel.gamesViewModel.MazeGameViewModel

class MazeGame : Fragment() {



    private  val viewModel: MazeGameViewModel by lazy{
        ViewModelProvider(this)[MazeGameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.maze_game_fragment, container, false)
    }



}