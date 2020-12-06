package com.itmo.lab5belov

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    val pics: MutableList<Picture> = mutableListOf();

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name = item.findViewById<TextView>(R.id.name);
        val pic = item.findViewById<ImageView>(R.id.picture);

        fun bind(element: Picture, position: Int) {
            name.text = "Author: " + element.photographer;
            Picasso.get().load(element.src["landscape"]).into(pic);
        }
    }

    fun addPics(data: List<Picture>) {
        pics += data;
        notifyDataSetChanged();
    }

    fun removeAllPics() {
        pics.clear();
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val layout = inflater.inflate(R.layout.tile, parent, false);
        return MyViewHolder(layout);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(pics[position], position);
    }

    override fun getItemCount(): Int {
        return pics.size;
    }
}