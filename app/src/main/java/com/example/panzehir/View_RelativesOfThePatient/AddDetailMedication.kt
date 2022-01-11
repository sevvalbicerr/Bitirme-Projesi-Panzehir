package com.example.panzehir.View_RelativesOfThePatient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentAddDetailMedicationBinding
import com.example.panzehir.databinding.FragmentAddMedicationPageBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.MainActivity
import com.google.firebase.firestore.FirebaseFirestore


class AddDetailMedication : Fragment() {
    private var _binding: FragmentAddDetailMedicationBinding?=null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager
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
        preferenceManager = context?.let { PreferenceManager(it) }!!
        binding.recordButton.setOnClickListener{
            recordMedication(it)
        }
        binding.closeButton.setOnClickListener{
            //Firebase'e ilacı kaydetmeden ve önceki sayfaya dön
            Navigation.findNavController(it).navigate(R.id.action_addDetailMedication_to_addMedicationPage)
        }
    }

    fun recordMedication(view: View){
        val medication: HashMap<String, Any> = HashMap()
        val tc=preferenceManager.getString(Constants.KEY_ID_PATIENT)!!
        medication[ConstantsForRelativesMedication.KEY_NAME_MEDICATION] = binding.nameOfMedicationEdittext.text.toString()
        medication[ConstantsForRelativesMedication.KEY_AMOUNT] = binding.amountofusageMedicationEdittext.text.toString()
        medication[ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT] = binding.hungryOrNotEdittext.text.toString()
        medication[ConstantsForRelativesMedication.KEY_TIME] = binding.timeOfMedicationEdittext.text.toString()
        medication[ConstantsForRelativesMedication.KEY_PATIENT_ID] = tc

        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_MEDICATION)
            .document()
            .set(medication)
            .addOnSuccessListener {

                Navigation.findNavController(view).navigate(R.id.action_addDetailMedication_to_addMedicationPage)
            }
            .addOnFailureListener { Toast.makeText(context, "Error: " + it.message, Toast.LENGTH_SHORT).show() }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}