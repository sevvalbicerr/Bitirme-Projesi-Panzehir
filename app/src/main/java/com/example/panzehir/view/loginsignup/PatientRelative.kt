package com.example.panzehir.view.loginsignup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.viewModel.PatientRelativeViewModel

class PatientRelative : Fragment() {

    private  val viewModel: PatientRelativeViewModel by lazy {
        ViewModelProvider(this)[PatientRelativeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.patient_relative_fragment, container, false)
    }



}