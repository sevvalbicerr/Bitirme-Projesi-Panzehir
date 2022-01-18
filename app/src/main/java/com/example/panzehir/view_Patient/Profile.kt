
package com.example.panzehir.view_Patient

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.ProfileFragmentBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.viewModelPatient.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore

class Profile : Fragment() {
    private var _binding: ProfileFragmentBinding?=null
    private val binding get() = _binding!!

    private lateinit var preferenceManager: PreferenceManager

    private  val viewModel: ProfileViewModel by lazy{
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=ProfileFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ProfileBackButton.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_profile_to_home2)
        }
        preferenceManager = context?.let { PreferenceManager(it) }!!
        println(preferenceManager.getString(Constants.KEY_BIRTHDAY_PATIENT))
        // The account will remain open until the sign-out button is clicked
       /* if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)){
            val intent = Intent(activity, HostFragment2::class.java)
            startActivity(intent)
            activity!!.finish()
        }*/
       getUserProfile()

    }
    fun getUserProfile(){
        //Patient

        binding.nameofPatientEdittext.text=preferenceManager.getString(Constants.KEY_FIRST_NAME_PATIENT)
        binding.surnameofPatientEdittext.text=preferenceManager.getString(Constants.KEY_LAST_NAME_PATIENT)
        binding.BloodEdittext.text=preferenceManager.getString(Constants.KEY_BLOOD_PATIENT)
        binding.TCEdittext.text=preferenceManager.getString(Constants.KEY_ID_PATIENT)
        binding.DateofBirthEdittext.text="14.01.1965"
        binding.weightEdittext.text=preferenceManager.getString(Constants.KEY_WEIGHT_PATIENT)
        binding.heightEdittext.text=preferenceManager.getString(Constants.KEY_HEIGHT_PATIENT)
        binding.sexEdittext.text=preferenceManager.getString(Constants.KEY_GENDER_PATIENT)
        binding.AdressEdittext.text=preferenceManager.getString(Constants.KEY_ADDRESS_PATIENT)
        binding.DateofBirthEdittext.text=preferenceManager.getString(Constants.KEY_BIRTHDAY_PATIENT)
        //Relatives of the Patient
        binding.nameofRelatevesEdittext.text=preferenceManager.getString(Constants.KEY_FIRST_NAME_RELATIVE_PATIENT)
        binding.surnameofRelativesEdittext.text=preferenceManager.getString(Constants.KEY_LAST_NAME_RELATIVE_PATIENT)
        binding.degreeEdittext.text=preferenceManager.getString(Constants.KEY_DEGREE)
        binding.emailEdittext.text=preferenceManager.getString(Constants.KEY_EMAIL)
        binding.numberEdittext.text=preferenceManager.getString(Constants.KEY_PHONE1)
        binding.number2Edittext.text=preferenceManager.getString(Constants.KEY_PHONE2)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}