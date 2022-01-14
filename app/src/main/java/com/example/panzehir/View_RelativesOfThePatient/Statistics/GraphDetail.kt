package com.example.panzehir.View_RelativesOfThePatient.Statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.FragmentGraphBinding
import com.example.panzehir.databinding.FragmentGraphDetailBinding
import com.example.panzehir.utilities.Constants
import com.example.panzehir.utilities.ConstantsForRelativesMedication
import com.example.panzehir.utilities.PreferenceManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GraphDetail : Fragment() {
    private var _binding: FragmentGraphDetailBinding?=null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager
    var  datelist=Array(30) { 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentGraphDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.GraphView.setData(generateRandomDataPoints())
        preferenceManager = context?.let { PreferenceManager(it) }!!

        binding.StatisticsTitle.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_graphDetail_to_graph) }

    }
    private fun generateRandomDataPoints(): List<DataPoint> {
        val random = Random()
        val list= arrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,10,15,20,21,50)
        //random.nextInt(50) + 1
        return (0..30).map {
            DataPoint(it, random.nextInt(50) + 1 )
        }
    }
    fun getWaterForStatistics():Array<Int>{

        val myUserId = preferenceManager.getString(Constants.KEY_ID_PATIENT)
        val date=getCurrentDate()
        val database = FirebaseFirestore.getInstance()
        database.collection(ConstantsForRelativesMedication.KEY_COLLECTION_WATER).get()
            .addOnCompleteListener{
                if (it.isSuccessful && it.result != null) {
                    for (documentSnapshot: QueryDocumentSnapshot in it.result) {
                        if(myUserId==documentSnapshot.get(Constants.KEY_ID_PATIENT).toString()){
                            datelist[14]= Integer.parseInt(documentSnapshot.getString("2022-01-14").toString())
                            datelist[15]= Integer.parseInt(documentSnapshot.getString("2022-01-15").toString())
                            datelist[16]= Integer.parseInt(documentSnapshot.getString("2022-01-16").toString())
                            datelist[17]= Integer.parseInt(documentSnapshot.getString("2022-01-17").toString())
                            datelist[18]= Integer.parseInt(documentSnapshot.getString("2022-01-17").toString())

                        }
                        println(datelist[14])
                        return@addOnCompleteListener
                    }
                }}

        return datelist
    }
    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }
    fun parseDate():Int{
        var SDFormat= SimpleDateFormat("yyyy-MM-dd")
        try {
            val dt = getCurrentDate()
            var x=dt.split("-")
            // println("x :${x[2]}")
            return Integer.parseInt(x[2])
        }
        catch (e: ParseException) {
            e.printStackTrace();
        }
        return 0
    }

}