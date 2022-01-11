@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view_Patient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.panzehir.adapters.MedicationRecyclerAdapter
import com.example.panzehir.databinding.MedicationTrackingFragmentBinding
import com.example.panzehir.model.medication
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.viewModelPatient.MedicationTrackingViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class MedicationTracking : Fragment() {
    private lateinit var preferenceManager: PreferenceManager
    private var medicaon_list = ArrayList<medication>()
    private lateinit var medicaonAdapter: MedicationRecyclerAdapter
    private var _binding: MedicationTrackingFragmentBinding?=null
    private val binding get() = _binding!!
    private lateinit var  adapter:MedicationRecyclerAdapter
    private  val viewModel: MedicationTrackingViewModel by lazy{
        ViewModelProvider(this)[MedicationTrackingViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=MedicationTrackingFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = context?.let { PreferenceManager(it) }!!
        binding.medicationRecycler.layoutManager=LinearLayoutManager(context)
        //binding.medicationRecycler.adapter=adapter

        medicaonAdapter = MedicationRecyclerAdapter(medicaon_list)
        binding.medicationRecycler.adapter = medicaonAdapter
        getMedication()
    }
    fun getMedication(){
        val myUserId = preferenceManager.getString(ConstantsForRelativesMedication.KEY_PATIENT_ID)
        println(myUserId+":+++++ userId")
        val database = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_MEDICATION)
            .whereEqualTo(ConstantsForRelativesMedication.KEY_PATIENT_ID,myUserId)
            .get()
            .addOnCompleteListener {

                if (it.isSuccessful && it.result != null) {
                    println("  if (it.isSuccessful && it.result != null) ")
                    medicaon_list.clear()
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        println(" for ")

                        val medication = medication()
                        medication.nameOfMedication = documentSnapshot.getString(ConstantsForRelativesMedication.KEY_NAME_MEDICATION).toString()
                        medication.amountofusageMedication = documentSnapshot.getString(ConstantsForRelativesMedication.KEY_AMOUNT).toString()
                        medication.hungryOrNot = documentSnapshot.getString(ConstantsForRelativesMedication.KEY_HUNGRY_OR_NOT).toString()
                        medication.time = documentSnapshot.getString(ConstantsForRelativesMedication.KEY_TIME).toString()
                        medicaon_list.add(medication)

                    }
                    if (medicaon_list.size > 0) {
                        medicaonAdapter.notifyDataSetChanged()
                    }
                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}