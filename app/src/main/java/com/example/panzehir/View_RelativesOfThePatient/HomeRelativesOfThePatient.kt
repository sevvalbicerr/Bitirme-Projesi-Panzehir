package com.example.panzehir.View_RelativesOfThePatient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.CardMachingGameFragmentBinding
import com.example.panzehir.databinding.FragmentHomeRelativesOfThePatientBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import java.util.*

class HomeRelativesOfThePatient : Fragment() {
    private var _binding: FragmentHomeRelativesOfThePatientBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeRelativesOfThePatientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = context?.let { PreferenceManager(it) }!!
        getUser()
        binding.seeMoreMedication.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeRelativesOfThePatient_to_addMedicationPage)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun getUser(){
        binding.nameOfPerson.text=preferenceManager.getString(Constants.KEY_FIRST_NAME_PATIENT)+" "+preferenceManager.getString(
            Constants.KEY_LAST_NAME_PATIENT)
        binding.typeOfBlood.text=preferenceManager.getString(Constants.KEY_BLOOD_PATIENT)
        val now = Calendar.getInstance()
        val nowYear=now.get(Calendar.YEAR)
//        println("nowYear  "+ nowYear)
        //birthday kullanıcı bilgisi boş geliyor.
        //val age=nowYear-preferenceManager.getString(Constants.KEY_BIRTHDAY_PATIENT)!!.toInt()
        //binding.typeOfBlood.text=age
    }


}