package com.example.panzehir.view_Patient.memories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.panzehir.R
import com.example.panzehir.adapters.MenoriesGridViewAdapter
import com.example.panzehir.databinding.MemoriesFragmentBinding
import com.example.panzehir.model.memoriesGridModel
import com.example.panzehir.viewModelPatient.memoriesViewModel.MemoriesViewModel

class Memories : Fragment() {

    var listOfPosts= ArrayList<memoriesGridModel>()
    private var _binding: MemoriesFragmentBinding?=null
    private val binding get() = _binding!!

   lateinit var  adapter : MenoriesGridViewAdapter
    var images= intArrayOf(
        R.drawable.boy,
        R.drawable.daughter,
        R.drawable.torun,
        R.drawable.torun2
    )
    var names= arrayOf(
        "Oğlum",
        "Kızım",
        "Oğlumun kızı",
        "Kızımın oğlu"
    )



    private  val viewModel: MemoriesViewModel by lazy {
        ViewModelProvider(this)[MemoriesViewModel::class.java]
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=MemoriesFragmentBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       //GridView Memories sayfası için adaptere gönderilecek liste
        for (i in names.indices){
            listOfPosts.add(memoriesGridModel(names[i],images[i]))
        }
        //Gridview Adapter
        adapter= MenoriesGridViewAdapter(listOfPosts,context!!)
        binding.memoriesGridView.adapter=adapter
        setupGridView()
    }
    private fun setupGridView() {
        binding.memoriesGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    context, " Clicked Position: " + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}