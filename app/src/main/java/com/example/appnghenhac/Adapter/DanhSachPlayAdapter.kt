package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Main.DanhSachBaiHatActivity
import com.example.appnghenhac.Model.PlayList
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso

class DanhSachPlayListAdapter(val context: Context, val data: ArrayList<PlayList>) : RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_danh_sach_play_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNamePlayList.text=data.get(position).ten
        Picasso.with(context).load(data[position].icon).into(holder.imageView)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, DanhSachBaiHatActivity::class.java)
            intent.putExtra("idPlaylist",data.get(position))
            context.startActivity(intent)
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgDanhSachCacPlayList)
        val txtNamePlayList = view.findViewById<TextView>(R.id.txtTenDanhSachPlayList)
    }


}