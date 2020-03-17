package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Main.DanhSachBaiHatActivity
import com.example.appnghenhac.Model.TheLoai
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ds_theloai.view.*

class DanhSachTheLoaiAdapter (val context: Context, val data: ArrayList<TheLoai>): RecyclerView.Adapter<DanhSachTheLoaiAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhSachTheLoaiAdapter.ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_ds_theloai, parent, false)
        return  ViewHolder(view)
    }

    class ViewHolder (view:View): RecyclerView.ViewHolder(view){
        fun bind(data: ArrayList<TheLoai>, position: Int){
            Picasso.with(itemView.context).load(data[position].hinhTheLoai).into(itemView.imgTheLoai)
            itemView.txtNameTheLoai.text=data[position].tenTheLoai
            itemView.setOnClickListener {
                val intent: Intent = Intent(itemView.context, DanhSachBaiHatActivity::class.java)
                intent.putExtra("idptheloai",data[position]) // data[posotion]  la kieu du lieu The Loai

                itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DanhSachTheLoaiAdapter.ViewHolder, position: Int) {
        holder.bind(data,position)
    }

}