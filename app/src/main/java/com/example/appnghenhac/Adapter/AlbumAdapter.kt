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
import com.example.appnghenhac.Model.Album
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import okhttp3.internal.Internal

class AlbumAdapter (val context: Context, val data: ArrayList<Album>) :RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.layout_item_album,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(context).load(data.get(position).hinhAlBum).into(holder.imgAlbum)
        holder.txtTenAlBum.text=data.get(position).tenAlBum
        holder.txtTenCaSi.text=data.get(position).tenCaSiAlbum
        holder.itemView.setOnClickListener {
            val intent= Intent(context,DanhSachBaiHatActivity::class.java)
            intent.putExtra("idAlBum",data[position])
            context.startActivity(intent)
        }
    }

    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {
        val imgAlbum=view.findViewById<ImageView>(R.id.imgAlbum)
        val txtTenAlBum=view.findViewById<TextView>(R.id.txtTenAlBum)
        val txtTenCaSi= view.findViewById<TextView>(R.id.txtTenCaSiAlbum)


    }
}