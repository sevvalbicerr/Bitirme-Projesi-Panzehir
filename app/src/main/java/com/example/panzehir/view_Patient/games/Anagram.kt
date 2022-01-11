package com.example.panzehir.view_Patient.games

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
import com.example.panzehir.databinding.AnagramFragmentBinding
import com.example.panzehir.databinding.GamesFragmentBinding
import com.example.panzehir.viewModelPatient.AnagramViewModel

class Anagram : Fragment() {

    private var _binding: AnagramFragmentBinding?=null
    private val binding get() = _binding!!
    private var firstWord: String? = null
    private var Answer: String? = null
    private  val viewModel: AnagramViewModel by lazy {
        ViewModelProvider(this)[AnagramViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=AnagramFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newGame()
        binding.imageBack.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_anagram2_to_games2) }
        binding.validate.setOnClickListener {
            val checkControl = control()
            if (checkControl) {
                val builder = AlertDialog.Builder(this.context)
                builder.setTitle("${firstWord} kelimesi ${Answer} kelimesiyle anagram kelimedir.")
                builder.setPositiveButton("Yeni Oyun") { dialog, which ->
                    builder.setCancelable(true)
                    newGame()
                    binding.Answer.text=" "
                }
                builder.show()

            } else {
                Toast.makeText(context, "Tekrar deneyin", Toast.LENGTH_LONG).show()
                binding.Answer.text = " "
            }
        }
        binding.newGame.setOnClickListener {
            binding.Answer.text=" "
            newGame()
        }

    }
    private fun control():Boolean{
        //Kontrol et butonu
        var control=false
        firstWord?.let {
            Answer=viewModel.findAnagram(it)
            //binding.Answer.text=Answer
        }
        //kontrol et butonu
        //edittextTeki giriş kontrol edilecek
        var string=binding.wordEnteredTv.text.toString()
        if (Answer==string){
            Toast.makeText(this.context,"Doğru Cevap !!!!", Toast.LENGTH_LONG).show()
            binding.Answer.text=Answer

            control=true
        }
        else{
            Toast.makeText(this.context,"Yanlış Cevap !!!!", Toast.LENGTH_LONG).show()

        }
        return control
    }
    private fun newGame() {
        firstWord = viewModel.randomWord()
        binding.wordTv.text = firstWord
        binding.wordEnteredTv.setText("")

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}