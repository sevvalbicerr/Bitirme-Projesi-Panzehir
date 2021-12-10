package com.example.panzehir.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.viewModel.MedicationTrackingViewModel

class MedicationTracking : Fragment() {

  

    private  val viewModel: MedicationTrackingViewModel by lazy{
        ViewModelProvider(this)[MedicationTrackingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.medication_tracking_fragment, container, false)
    }



}