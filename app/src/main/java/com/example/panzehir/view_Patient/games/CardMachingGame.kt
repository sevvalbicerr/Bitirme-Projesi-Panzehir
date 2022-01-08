@file:Suppress("UNREACHABLE_CODE")

package com.example.panzehir.view_Patient.games

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.panzehir.R
import com.example.panzehir.databinding.ActivityMainBinding
import com.example.panzehir.databinding.CardMachingGameFragmentBinding
import com.example.panzehir.viewModelPatient.gamesViewModel.CardMachingGameViewModel
import com.example.panzehir.view_Patient.MainActivity
import kotlin.properties.Delegates

class CardMachingGame : Fragment() {

    private var _binding: CardMachingGameFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CardMachingGameViewModel by lazy {
        ViewModelProvider(this)[CardMachingGameViewModel::class.java]
    }
    //array for the images
    private var cardsArray = mutableListOf(101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206)
    //Delegates lateinit gibi sonradan initialize edilecekler için kullanılıyor
    private var image101 by Delegates.notNull<Int>()
    private var image102 by Delegates.notNull<Int>()
    private var image103 by Delegates.notNull<Int>()
    private var image104 by Delegates.notNull<Int>()
    private var image105 by Delegates.notNull<Int>()
    private var image106 by Delegates.notNull<Int>()
    private var image201 by Delegates.notNull<Int>()
    private var image202 by Delegates.notNull<Int>()
    private var image203 by Delegates.notNull<Int>()
    private var image204 by Delegates.notNull<Int>()
    private var image205 by Delegates.notNull<Int>()
    private var image206 by Delegates.notNull<Int>()

    private var firstCard by Delegates.notNull<Int>()
    private var secondCard by Delegates.notNull<Int>()
    private var clickedFirst by Delegates.notNull<Int>()
    private var clickedSecond by Delegates.notNull<Int>()
    private var cardNumber = 1

    private var turn = 1
    private var playerPoints = 0
    private var cpuPoint = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CardMachingGameFragmentBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        includedImages()
        includedImagesSetonClickListener()
        frontOfCardsResources()
        //suffle the images
        cardsArray.shuffle()
        binding.tvP2.setTextColor(Color.GRAY)

    }
    private fun includedImagesSetonClickListener(){
        binding.includeImages.iv11.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff(binding.includeImages.iv11, theCard)
        }
        binding.includeImages.iv12.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff(binding.includeImages.iv12, theCard)}
        binding.includeImages.iv13.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff(binding.includeImages.iv13, theCard)}
        binding.includeImages.iv14.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff(binding.includeImages.iv14, theCard)}
        binding.includeImages.iv21.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv21, theCard)
        }
        binding.includeImages.iv22.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv22, theCard)
        }
        binding.includeImages.iv23.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv23, theCard)
        }
        binding.includeImages.iv24.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv24, theCard)
        }
        binding.includeImages.iv31.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv31, theCard)
        }
        binding.includeImages.iv32.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv32, theCard)
        }
        binding.includeImages.iv33.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv33, theCard)
        }
        binding.includeImages.iv34.setOnClickListener { v ->
            val theCard: Int = v.tag.toString().toInt()
            println(theCard)
            doStuff( binding.includeImages.iv34, theCard)
        }


    }
    private fun includedImages(){
        binding.includeImages.iv11.tag = 0
        binding.includeImages.iv12.tag = 1
        binding.includeImages.iv13.tag = 2
        binding.includeImages.iv14.tag = 3
        binding.includeImages.iv21.tag = 4
        binding.includeImages.iv22.tag = 5
        binding.includeImages.iv23.tag = 6
        binding.includeImages.iv24.tag = 7
        binding.includeImages.iv31.tag = 8
        binding.includeImages.iv32.tag = 9
        binding.includeImages.iv33.tag = 10
        binding.includeImages.iv34.tag = 11
    }

    private fun frontOfCardsResources() {
        image101 = R.drawable.ic_image101
        image102 = R.drawable.ic_image102
        image103 = R.drawable.ic_image103
        image104 = R.drawable.ic_image104
        image105 = R.drawable.ic_image105
        image106 = R.drawable.ic_image106
        image201 = R.drawable.ic_image201
        image202 = R.drawable.ic_image202
        image203 = R.drawable.ic_image203
        image204 = R.drawable.ic_image204
        image205 = R.drawable.ic_image205
        image206 = R.drawable.ic_image206
    }

    private fun doStuff(iv: ImageView, card: Int) {
        //set the correct image to the imageview
        when (cardsArray[card]) {
            101 -> iv.setImageResource(image101)
            102 -> iv.setImageResource(image102)
            103 -> iv.setImageResource(image103)
            104 -> iv.setImageResource(image104)
            105 -> iv.setImageResource(image105)
            106 -> iv.setImageResource(image106)

            201 -> iv.setImageResource(image201)
            202 -> iv.setImageResource(image202)
            203 -> iv.setImageResource(image203)
            204 -> iv.setImageResource(image204)
            205 -> iv.setImageResource(image205)
            206 -> iv.setImageResource(image206)
        }
        if (cardNumber == 1) {
            firstCard = cardsArray[card]
            if (firstCard > 200) {
                firstCard -= 100
            }
            cardNumber = 2
            clickedFirst = card

            iv.isEnabled = false
        } else if (cardNumber==2)  {
            secondCard = cardsArray[card]
            if (secondCard > 200) {
                secondCard -= 100
            }
            cardNumber = 1
            clickedSecond = card
            imageViewIsEnabled(false)
            if (firstCard==secondCard){

            }

            object : CountDownTimer(1500, 3000) {
                override fun onFinish() {
                    //check if the selected images are equal
                    calculate()
                }

                override fun onTick(p0: Long) {
                    Log.d("SplashActivity", p0.toString())
                }
            }.start()
    }}

    fun imageViewIsEnabled(b: Boolean) {
        binding.includeImages.iv11.isEnabled = b
        binding.includeImages.iv12.isEnabled = b
        binding.includeImages.iv13.isEnabled = b
        binding.includeImages.iv14.isEnabled = b
        binding.includeImages.iv21.isEnabled = b
        binding.includeImages.iv22.isEnabled = b
        binding.includeImages.iv23.isEnabled = b
        binding.includeImages.iv24.isEnabled = b
        binding.includeImages.iv31.isEnabled = b
        binding.includeImages.iv32.isEnabled = b
        binding.includeImages.iv33.isEnabled = b
        binding.includeImages.iv34.isEnabled = b
    }

    private fun calculate() {
        //if images are equal remove tgem and add point
        if (firstCard == secondCard) {
            when (clickedFirst) {
                0 ->  binding.includeImages.iv11.visibility = View.INVISIBLE
                1 ->  binding.includeImages.iv12.visibility = View.INVISIBLE
                2 ->  binding.includeImages.iv13.visibility = View.INVISIBLE
                3 ->  binding.includeImages.iv14.visibility = View.INVISIBLE
                4 ->  binding.includeImages.iv21.visibility = View.INVISIBLE
                5 ->  binding.includeImages.iv22.visibility = View.INVISIBLE
                6 ->  binding.includeImages.iv23.visibility = View.INVISIBLE
                7 ->  binding.includeImages.iv24.visibility = View.INVISIBLE
                8 ->  binding.includeImages.iv31.visibility = View.INVISIBLE
                9 ->  binding.includeImages.iv32.visibility = View.INVISIBLE
                10 ->  binding.includeImages.iv33.visibility = View.INVISIBLE
                11 ->  binding.includeImages.iv34.visibility = View.INVISIBLE
            }

            when (clickedSecond) {
                0 ->  binding.includeImages.iv11.visibility = View.INVISIBLE
                1 ->  binding.includeImages.iv12.visibility = View.INVISIBLE
                2 ->  binding.includeImages.iv13.visibility = View.INVISIBLE
                3 ->  binding.includeImages.iv14.visibility = View.INVISIBLE
                4 ->  binding.includeImages.iv21.visibility = View.INVISIBLE
                5 ->  binding.includeImages.iv22.visibility = View.INVISIBLE
                6 ->  binding.includeImages.iv23.visibility = View.INVISIBLE
                7 ->  binding.includeImages.iv24.visibility = View.INVISIBLE
                8 ->  binding.includeImages.iv31.visibility = View.INVISIBLE
                9 ->  binding.includeImages.iv32.visibility = View.INVISIBLE
                10 ->  binding.includeImages.iv33.visibility = View.INVISIBLE
                11 ->  binding.includeImages.iv34.visibility = View.INVISIBLE
            }

            //add points to the corrent player
            if (turn == 1) {
                playerPoints++
                binding.tvP1.text = "P1: $playerPoints"
            } else {
                if (turn == 2) {
                    cpuPoint++
                    binding.tvP2.text = "P2: $cpuPoint"
                }
            }
        } else {
            binding.includeImages.iv11.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv12.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv13.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv14.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv21.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv22.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv23.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv24.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv31.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv32.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv33.setImageResource(R.drawable.ic_back)
            binding.includeImages.iv34.setImageResource(R.drawable.ic_back)

            //change the player turn
            if (turn == 1) {
                turn = 2
                binding.tvP1.setTextColor(Color.GRAY)
                binding.tvP2.setTextColor(Color.BLACK)
            } else if (turn == 2) {
                turn = 1
                binding.tvP2.setTextColor(Color.GRAY)
                binding.tvP1.setTextColor(Color.BLACK)
            }
        }

        imageViewIsEnabled(true)

        //check if the game is over
        checkEnd()
    }

    private fun checkEnd() {
        if ( binding.includeImages.iv11.visibility == View.INVISIBLE &&
            binding.includeImages.iv12.visibility == View.INVISIBLE &&
            binding.includeImages.iv13.visibility == View.INVISIBLE &&
            binding.includeImages.iv14.visibility == View.INVISIBLE &&
            binding.includeImages.iv21.visibility == View.INVISIBLE &&
            binding.includeImages.iv22.visibility == View.INVISIBLE &&
            binding.includeImages.iv23.visibility == View.INVISIBLE &&
            binding.includeImages.iv24.visibility == View.INVISIBLE &&
            binding.includeImages.iv31.visibility == View.INVISIBLE &&
            binding.includeImages.iv32.visibility == View.INVISIBLE &&
            binding.includeImages.iv33.visibility == View.INVISIBLE &&
            binding.includeImages.iv34.visibility == View.INVISIBLE
        ) {
            val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialogBuilder
                .setMessage("GAME OVER!\nP1: $playerPoints\nP2: $cpuPoint")
                .setCancelable(false)
                .setNegativeButton("EXIT") { _, _ ->
                    findNavController().navigate(R.id.action_cardMachingGame2_to_games2)
                    //val alertDialog: AlertDialog = alertDialogBuilder.create()
                   // alertDialog.show()
                }.show()
        }

    }
}