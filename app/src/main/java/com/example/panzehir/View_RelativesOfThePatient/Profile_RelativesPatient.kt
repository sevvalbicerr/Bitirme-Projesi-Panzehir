package com.example.panzehir.View_RelativesOfThePatient

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentLoginBinding
import com.example.panzehir.databinding.FragmentProfileRelativesPatientBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.example.panzehir.view_Patient.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

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
        //update-edit profile
        binding.imageEditProfile.setOnClickListener{
            binding.closeButton.visibility=View.VISIBLE
            binding.UpdateButton.visibility=View.VISIBLE
            binding.EditProfile.visibility=View.VISIBLE
            binding.Profile.visibility=View.INVISIBLE
        }

        //user profile
        getUserProfile()
        //update user profile
        /*
        bu güncelle butonuna basılınca aşağıdaki fonksiyon çalışıyor , kaydet butonuna basıldığı anda
        uygulama patlayıp yeniden çalışıyor gibi bir durum oluyor
        bunu da anlamadım
        * */
        binding.UpdateButton.setOnClickListener{
        //UpdateUserProfile()
        }
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
    private fun UpdateUserProfile(){
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        val user: HashMap<String, Any> = HashMap()

        // Patient Information
        user[Constants.KEY_FIRST_NAME_PATIENT] = binding.nameofPatientEdittext.text.toString()
        user[Constants.KEY_LAST_NAME_PATIENT] = binding.surnameofPatientEdittext.text.toString()

        user[Constants.KEY_ID_PATIENT] = binding.TCEdittext.text.toString()
        user[Constants.KEY_WEIGHT_PATIENT] = binding.weightEdittext.text.toString()
        user[Constants.KEY_HEIGHT_PATIENT] = binding.heightEdittext.text.toString()
        user[Constants.KEY_ADDRESS_PATIENT] = binding.AdressEdittext.text.toString()
        // Patient Relative Information
        user[Constants.KEY_FIRST_NAME_RELATIVE_PATIENT] = binding.nameofRelatevesEdittext.text.toString()
        user[Constants.KEY_LAST_NAME_RELATIVE_PATIENT] = binding.surnameofRelativesEdittext.text.toString()
        user[Constants.KEY_EMAIL] = binding.emailEdittext.text.toString()
        user[Constants.KEY_PHONE1] = binding.numberEdittext.text.toString()
        user[Constants.KEY_PHONE2] = binding.number2Edittext.text.toString()


        //doğum tarihini deneyeceğim
        user[Constants.KEY_BIRTHDAY_PATIENT]=binding.DateofBirthEdittext.text.toString()
        /////Spinner Problem olduuuu
        user[Constants.KEY_DEGREE]=preferenceManager.getString(Constants.KEY_DEGREE).toString()
        user[Constants.KEY_GENDER_PATIENT]=preferenceManager.getString(Constants.KEY_GENDER_PATIENT).toString()
        user[Constants.KEY_BLOOD_PATIENT]=preferenceManager.getString(Constants.KEY_BLOOD_PATIENT).toString()


        /*Password problem oldu

         println("preferenceManager.getString(Constants.KEY_PASSWORD) ${preferenceManager.getString(Constants.KEY_PASSWORD)}")
         bu satırı yazarak kontrol de ettim bunun içi boş geliyor.
         bu yüzden elle verdim ama değiştirilmeli
        **/
        user[Constants.KEY_PASSWORD] = "123456"
        user[Constants.KEY_PASSWORD_ACCOUNT] = "123"

        database.collection(Constants.KEY_COLLECTION_USERS)
            .document(binding.TCEdittext.text.toString())
            .set(user)
            .addOnSuccessListener {
                preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                // Patient Information
                preferenceManager.putString(Constants.KEY_FIRST_NAME_PATIENT,binding.nameofPatientEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_LAST_NAME_PATIENT,binding.surnameofPatientEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_ID_PATIENT,binding.TCEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_WEIGHT_PATIENT,binding.weightEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_HEIGHT_PATIENT,binding.heightEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_ADDRESS_PATIENT,binding.AdressEdittext.text.toString())
                // Patient Relative Information
                preferenceManager.putString(Constants.KEY_FIRST_NAME_RELATIVE_PATIENT,binding.nameofRelatevesEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_LAST_NAME_RELATIVE_PATIENT,binding.surnameofRelativesEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_EMAIL,binding.emailEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_PHONE1,binding.numberEdittext.text.toString())
                preferenceManager.putString(Constants.KEY_PHONE2,binding.number2Edittext.text.toString())

                val intent = Intent(activity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener { Toast.makeText(context, "Error: " + it.message, Toast.LENGTH_SHORT).show() }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}