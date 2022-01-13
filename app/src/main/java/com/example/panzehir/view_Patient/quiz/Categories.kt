package com.example.panzehir.view_Patient.quiz

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentCategoriesBinding
import com.example.panzehir.databinding.HomeFragmentBinding

class Categories : Fragment() {
    private var _binding: FragmentCategoriesBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.poverbQuiz.setOnClickListener {
            val bundle = bundleOf("type" to "poverb")
            Navigation.findNavController(it).navigate(R.id.action_categories_to_quiz2,bundle)}
        binding.expression.setOnClickListener {
            val bundle = bundleOf("type" to "expression")
            Navigation.findNavController(it).navigate(R.id.action_categories_to_quiz2,bundle)}

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}