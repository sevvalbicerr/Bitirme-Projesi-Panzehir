package com.example.panzehir.view_Patient

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.HomeFragmentBinding
import com.example.panzehir.viewModelPatient.HomeViewModel

class Home : Fragment() {
    private var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!

    private  val viewModel: HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation
        binding.ProfileLinearLayout.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_home2_to_profile) }
        binding.seeMoreMemories.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_home2_to_memories2) }
        binding.seeMoreMedication.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_home2_to_medicationTracking) }

        binding.callEmergencyLayout.setOnClickListener { emergencyAlertButton() }
        binding.callFamilyLayout.setOnClickListener { callMyFamilyAlertButton(it) }

    }

    private fun callMyFamilyAlertButton(view: View) { Navigation.findNavController(view).navigate(R.id.action_home2_to_friendList) }

    fun emergencyAlertButton(){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Arama")
        builder.setMessage("Acil Durum Hattını Aramak İstiyor Musunuz?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Ara") { dialog, which ->
            Toast.makeText(this.context,
                "Aranılıyor...", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Arama") { dialog, which ->
            Toast.makeText(this.context,
                "Vazgeçildi.", Toast.LENGTH_SHORT).show()
        }
        builder.show()


    }
}