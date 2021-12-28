package com.example.panzehir.view_Patient.games

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        binding.validate.setOnClickListener {

            firstWord?.let {
                Answer=viewModel.findAnagram(it)
                binding.Answer.text=Answer
            }
            //kontrol et butonu
            //edittextTeki giriş kontrol edilecek
            var string=binding.wordEnteredTv.text.toString()
            if (Answer==string){
                println("Answer değişlkeni::$Answer  \n String değişkeni::$string")
                Toast.makeText(this.context,"Doğru Cevap !!!!", Toast.LENGTH_LONG).show()
                print("Doğru cevap anagram kelimeyi buldunuz")
            }
            else{
                println("Answer değişlkeni::$Answer  \n String değişkeni::$string")
                println("Yanlış cevap ")
            }
        }
        binding.newGame.setOnClickListener {
            binding.Answer.text=" "
            newGame()
        }
        binding.imageBack.setOnClickListener{
            //Navigation ekle
        }
    }
    private fun newGame() {
        firstWord = viewModel.randomWord()
        binding.wordTv.text = firstWord
        binding.wordEnteredTv.setText("")

    }



}