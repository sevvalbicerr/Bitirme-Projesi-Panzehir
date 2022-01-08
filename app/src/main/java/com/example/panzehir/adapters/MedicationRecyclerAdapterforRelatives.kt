package com.example.panzehir.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panzehir.databinding.MedicationxmlfileBinding
import com.example.panzehir.model.User
import com.example.panzehir.model.medication

//Firebase'den hasta yakınının girdiği ilaç listesi çekilerek bağlanacak
//Bu işlem verilerin firebase kaydından sonra yapılacak
class MedicationRecyclerAdapterforRelatives(private var medicationlist: ArrayList<medication>):RecyclerView.Adapter<MedicationRecyclerAdapterforRelatives.MedicationViewHolder>(){
    class MedicationViewHolder(var itemBinding:MedicationxmlfileBinding):RecyclerView.ViewHolder(itemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val myView= MedicationxmlfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MedicationViewHolder(myView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
            holder.itemBinding.checkBox.text = medicationlist[position].time+"\'da "+medicationlist[position].hungryOrNot+" "+
                    medicationlist[position].nameOfMedication+
                    " "+ medicationlist[position].amountofusageMedication+" tane"

    }

    override fun getItemCount(): Int {
        return medicationlist.size
    }
}