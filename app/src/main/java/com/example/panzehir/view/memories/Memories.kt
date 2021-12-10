package com.example.panzehir.view.memories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.panzehir.R
import com.example.panzehir.viewModel.memoriesViewModel.MemoriesViewModel

class Memories : Fragment() {



    private  val viewModel: MemoriesViewModel by lazy {
        ViewModelProvider(this)[MemoriesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.memories_fragment, container, false)
    }



}