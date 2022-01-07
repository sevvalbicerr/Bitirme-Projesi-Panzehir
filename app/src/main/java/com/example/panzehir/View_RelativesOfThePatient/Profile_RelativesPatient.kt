package com.example.panzehir.View_RelativesOfThePatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentLoginBinding
import com.example.panzehir.databinding.FragmentProfileRelativesPatientBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager

class Profile_RelativesPatient : Fragment() {
    private var _binding: FragmentProfileRelativesPatientBinding?=null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentProfileRelativesPatientBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceManager = context?.let { PreferenceManager(it) }!!

        binding.imageEditProfile.setOnClickListener{
            binding.closeButton.visibility=View.VISIBLE
            binding.recordButton.visibility=View.VISIBLE
            binding.EditProfile.visibility=View.VISIBLE
            binding.Profile.visibility=View.INVISIBLE
        }
        getUserProfile()
    }
    fun getUserProfile(){
        //Patient
        binding.nameofPatientEdittext.setText(preferenceManager.getString(Constants.KEY_FIRST_NAME_PATIENT))
        binding.surnameofPatientEdittext.setText(preferenceManager.getString(Constants.KEY_LAST_NAME_PATIENT))
        binding.BloodEdittext.setText(preferenceManager.getString(Constants.KEY_BLOOD_PATIENT))
        binding.TCEdittext.setText(preferenceManager.getString(Constants.KEY_ID_PATIENT))
        binding.DateofBirthEdittext.setText(preferenceManager.getString(Constants.KEY_BIRTHDAY_PATIENT))
        binding.weightEdittext.setText(preferenceManager.getString(Constants.KEY_WEIGHT_PATIENT))
        binding.heightEdittext.setText(preferenceManager.getString(Constants.KEY_HEIGHT_PATIENT))
        binding.sexEdittext.setText(preferenceManager.getString(Constants.KEY_GENDER_PATIENT))
        binding.AdressEdittext.setText(preferenceManager.getString(Constants.KEY_ADDRESS_PATIENT))
        //Relatives of the Patient
        binding.nameofRelatevesEdittext.setText(preferenceManager.getString(Constants.KEY_FIRST_NAME_RELATIVE_PATIENT))
        binding.surnameofRelativesEdittext.setText(preferenceManager.getString(Constants.KEY_LAST_NAME_RELATIVE_PATIENT))
        binding.degreeEdittext.setText(preferenceManager.getString(Constants.KEY_DEGREE))
        binding.emailEdittext.setText(preferenceManager.getString(Constants.KEY_EMAIL))
        binding.numberEdittext.setText(preferenceManager.getString(Constants.KEY_PHONE1))
        binding.number2Edittext.setText(preferenceManager.getString(Constants.KEY_PHONE2))
    }
}