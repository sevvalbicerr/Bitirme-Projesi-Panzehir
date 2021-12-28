package com.example.panzehir.view_Patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.panzehir.R
import com.example.panzehir.databinding.ActivityHostFragment2Binding
import com.example.panzehir.view_Patient.games.Games
import com.google.android.material.bottomnavigation.BottomNavigationView

class HostFragment2 : AppCompatActivity() {
    private var _binding: ActivityHostFragment2Binding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityHostFragment2Binding.inflate(layoutInflater)
        setContentView(binding.root)
      /*  binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_game->{
                   // supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView3, Games()).commit()
                    //Geriye basınca login sayfasına gidiyor niye ?
                }
                R.id.navigation_list-> println("Bu mesaj senin için büşbüş ")
            }
            true
        }*/
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_game ->{findNavController(R.id.fragmentContainerView3).navigate(R.id.games2)}
                R.id.navigation_list ->{findNavController(R.id.fragmentContainerView3).navigate(R.id.quiz2)}
            }
             true
        }
        // Home Fragment
        binding.fab.setOnClickListener { findNavController(R.id.fragmentContainerView3).navigate(R.id.home2) }

    }

}