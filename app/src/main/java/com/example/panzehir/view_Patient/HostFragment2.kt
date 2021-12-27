package com.example.panzehir.view_Patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.ActivityHostFragment2Binding
import com.example.panzehir.databinding.HomeFragmentBinding

class HostFragment2 : AppCompatActivity() {
    private var _binding: ActivityHostFragment2Binding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityHostFragment2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_game-> Navigation.findNavController(this,R.id.fragmentContainerView3)
                    .navigate(R.id.action_home2_to_games2)
                R.id.navigation_list-> println("Bu mesaj senin için büşbüş bu nereye gidecek anlamadım bağlayıver yukarıdaki gibi bebek djdjdjd")

            }
            true
        }

    }
}