package com.example.panzehir.View_RelativesOfThePatient.Statistics

import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentGraphBinding
import com.example.panzehir.databinding.SudokuFragmentBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Graph : Fragment() {

    private var _binding: FragmentGraphBinding?=null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentGraphBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = context?.let { PreferenceManager(it) }!!
        //getWaterForStatistics()
       binding.water.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_graph_to_graphDetail) }
        binding.StatisticsTitle.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_graph_to_homeRelativesOfThePatient) }

    }

}