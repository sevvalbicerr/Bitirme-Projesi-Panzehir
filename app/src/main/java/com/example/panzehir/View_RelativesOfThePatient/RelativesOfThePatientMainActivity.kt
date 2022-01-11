package com.example.panzehir.View_RelativesOfThePatient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.panzehir.R
import com.example.panzehir.databinding.ActivityHostFragment2Binding
import com.example.panzehir.databinding.ActivityRelativesOfThePatientMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class RelativesOfThePatientMainActivity : AppCompatActivity() {
    private var _binding: ActivityRelativesOfThePatientMainBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityRelativesOfThePatientMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.profileofrelativeBottommenu ->{findNavController(R.id.fragmentContainerView2).navigate(R.id.profile_RelativesPatient)}
                R.id.medicationtrackofrelativeBottommenu ->{findNavController(R.id.fragmentContainerView2).navigate(R.id.addMedicationPage)}
                R.id.SettingsOfbottomMenu ->{findNavController(R.id.fragmentContainerView2).navigate(R.id.settings2)}

            }
             true
        }
        // Home Fragment
        binding.fab.setOnClickListener { findNavController(R.id.fragmentContainerView2).navigate(R.id.homeRelativesOfThePatient) }
    }


}