package com.example.panzehir.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.panzehir.databinding.GridviewformemoriesfragmentBinding
import com.example.panzehir.model.memoriesGridModel

class AnagramGridViewAdapter(val listOfPosts: ArrayList<memoriesGridModel>,  context: Context
): BaseAdapter() {
    private lateinit var itemBinding: GridviewformemoriesfragmentBinding
    val inflater: LayoutInflater = LayoutInflater.from(context)
    //var context: Context? = null

    override fun getCount(): Int {
       return listOfPosts.size
    }

    override fun getItem(position: Int): Any {
        return listOfPosts.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view=convertView
        val holder: ItemViewHolder
        if (view == null) {
            itemBinding = GridviewformemoriesfragmentBinding.inflate(inflater)
            view = itemBinding.root
            holder = ItemViewHolder()
            holder.name = itemBinding.tv
            holder.icon = itemBinding.ivGallery
            view.tag = holder
        } else {
            holder = view.tag as ItemViewHolder
        }
        holder.name!!.text= this.listOfPosts[position].name
        holder.icon!!.setImageResource(this.listOfPosts[position].imageDrawable)
        return view

        //val myView= GridviewformemoriesfragmentBinding.inflate(LayoutInflater.from(parent!!.context),parent,false)


    }
    internal class ItemViewHolder {
        var name: TextView? = null
        var icon: ImageView? = null
    }

}