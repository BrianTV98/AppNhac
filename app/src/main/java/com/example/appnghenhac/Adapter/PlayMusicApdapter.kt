package com.example.appnghenhac.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R

class PlayMusicApdapter (val context: Context, val  data: ArrayList<BaiHat>): RecyclerView.Adapter<PlayMusicApdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("adaterPhay","ok chay dc")
        val view = LayoutInflater.from(context).inflate(R.layout.item_play_music, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmp= data.get(position)
        holder.txtMa.text= tmp.idBaiHat
        holder.txtTenBaiHat.text=tmp.tenBaiHat
        holder.txtTenCaSi.text=tmp.caSi
    }

    class ViewHolder(view:View): RecyclerView.ViewHolder(view ) {
        val txtMa= view.findViewById<TextView>(R.id.txtViewIndex)
        val txtTenBaiHat: TextView= view.findViewById(R.id.txtBaiHat)
        val txtTenCaSi: TextView= view.findViewById(R.id.txtTenCaSi)
    }


}