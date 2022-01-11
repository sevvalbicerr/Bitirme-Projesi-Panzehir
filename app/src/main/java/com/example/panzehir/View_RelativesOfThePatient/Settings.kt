package com.example.panzehir.View_RelativesOfThePatient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentAddMedicationPageBinding
import com.example.panzehir.databinding.FragmentSettingsBinding
import com.example.panzehir.loginsignup.Login
import com.example.panzehir.view_Patient.MainActivity

class Settings : Fragment() {
    private var _binding: FragmentSettingsBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.exit.setOnClickListener{
            val int= Intent(context,MainActivity::class.java)
            startActivity(int)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}