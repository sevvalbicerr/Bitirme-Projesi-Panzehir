package com.example.panzehir.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panzehir.databinding.RecyclerRowBinding
//Firebase'den hasta yakınının girdiği ilaç listesi çekilerek bağlanacak
//Bu işlem verilerin firebase kaydından sonra yapılacak
class MedicationRecyclerAdapter:RecyclerView.Adapter<MedicationRecyclerAdapter.MedicationViewHolder>(){
    class MedicationViewHolder(val itembinding:RecyclerRowBinding):RecyclerView.ViewHolder(itembinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val myView= RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MedicationViewHolder(myView)
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}