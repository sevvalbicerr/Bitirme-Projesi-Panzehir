@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view_Patient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.ProfileFragmentBinding
import com.example.panzehir.viewModelPatient.ProfileViewModel
class Profile : Fragment() {
    private var _binding: ProfileFragmentBinding?=null
    private val binding get() = _binding!!
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
    }

}