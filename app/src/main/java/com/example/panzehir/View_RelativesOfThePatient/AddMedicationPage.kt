package com.example.panzehir.View_RelativesOfThePatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentAddMedicationPageBinding
import com.example.panzehir.databinding.HomeFragmentBinding

class AddMedicationPage : Fragment() {
    private var _binding: FragmentAddMedicationPageBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentAddMedicationPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMedicationButton.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_addMedicationPage_to_addDetailMedication)
        }
    }
}