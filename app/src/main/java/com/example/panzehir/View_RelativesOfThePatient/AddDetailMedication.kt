package com.example.panzehir.View_RelativesOfThePatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentAddDetailMedicationBinding
import com.example.panzehir.databinding.FragmentAddMedicationPageBinding


class AddDetailMedication : Fragment() {
    private var _binding: FragmentAddDetailMedicationBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentAddDetailMedicationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recordButton.setOnClickListener{
            //Firebase'e ilacı kaydet ve önceki sayfaya dön
            Navigation.findNavController(it).navigate(R.id.action_addDetailMedication_to_addMedicationPage)
        }
        binding.closeButton.setOnClickListener{
            //Firebase'e ilacı kaydetmeden ve önceki sayfaya dön
            Navigation.findNavController(it).navigate(R.id.action_addDetailMedication_to_addMedicationPage)
        }
    }



}