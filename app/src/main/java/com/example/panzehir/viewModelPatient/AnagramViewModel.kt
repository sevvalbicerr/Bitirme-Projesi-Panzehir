package com.example.panzehir.viewModelPatient

import androidx.lifecycle.ViewModel
import java.util.*

class AnagramViewModel : ViewModel() {
    private val RANDOM = Random()
    private var word2:String?=null
    var wordForSorting= mutableListOf<Int>()
    var length = 0
    private val WORDS = arrayOf("elma",
        "lokal","bariz", "bitik","sanal",
        "kayseri",  "emeklilik","karşılama","kaymaklı"
        )
    private val WordsToAnagram= arrayOf("alem",
        "alkol","ibraz","bitki", "aslan",
        "kasiyer",  "iliklemek","karışlama","yakılmak"
    )
    private fun stringToInt(word:String){
        word2= word.uppercase(Locale.getDefault())
        val arrayByte= word2!!.toByteArray()
        val charASCII= mutableListOf<Int>()
        var j=0
        for (i in arrayByte){
            charASCII.add(i.toInt())
            j++
        }
        sort(charASCII)
    }
    fun randomWord(): String {
        return WORDS[RANDOM.nextInt(WORDS.size)]
    }
    private fun sort(array: MutableList<Int>) {
        if (array.isEmpty()) {
            return
        }
        wordForSorting = array
        length = array.size
        quickSort(0, length - 1)
    }

    private fun quickSort(lowerIndex: Int, higherIndex: Int) {
        var i = lowerIndex
        var j = higherIndex
        val pivot = wordForSorting[lowerIndex + (higherIndex - lowerIndex) / 2]
        while (i <= j) {
            while (pivot>wordForSorting[i]) {
                i++
            }
            while (pivot<wordForSorting[j]) {
                j--
            }
            if (i <= j) {
                exchangeNames(i, j)
                i++
                j--
            }
        }
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j)
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex)
        }

        //son durumda wordForSorting listesinde ilgili kelimenin ascıı numaralarının sıralanmış hali liste
        //şeklinde tutuluyor.


    }

    private fun exchangeNames(i: Int, j: Int) {
        val temp = wordForSorting[i]
        wordForSorting[i] = wordForSorting[j]
        wordForSorting[j] = temp
    }
    fun findAnagram(firstWord: String): String {
        var answer:String=""
        stringToInt(firstWord)
        val firstwordsorted= wordForSorting
        for(word in WordsToAnagram){
            stringToInt(word)
            if (firstwordsorted== wordForSorting){
                answer=word
            }
        }
        return answer
    }

}