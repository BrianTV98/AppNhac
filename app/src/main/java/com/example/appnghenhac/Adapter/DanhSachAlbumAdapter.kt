package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Main.DanhSachBaiHatActivity
import com.example.appnghenhac.Model.Album
import com.example.appnghenhac.Model.PlayList
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso

class DanhSachAlbumAdapter (val context: Context, val data: ArrayList<Album>):RecyclerView.Adapter<DanhSachAlbumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_ablum, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNameAlbum.text=data.get(position).tenAlBum
        holder.txtNamTenCaSiAlBum.text=data.get(position).tenCaSiAlbum
        Picasso.with(context).load(data[position].hinhAlBum).into(holder.imageView)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, DanhSachBaiHatActivity::class.java)
            intent.putExtra("idAlBum",data.get(position))
            context.startActivity(intent)
//            Toast.makeText(context,"Ok co the pass intent",Toast.LENGTH_LONG).show()
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgDanhSachCacAlbum)
        val txtNameAlbum = view.findViewById<TextView>(R.id.txtTenDanhSachAlbum)
        val txtNamTenCaSiAlBum=view.findViewById<TextView>(R.id.tenCaSiAlbum)
    }

}