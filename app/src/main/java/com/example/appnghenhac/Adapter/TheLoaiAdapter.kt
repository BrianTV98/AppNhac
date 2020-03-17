package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.ImageConverter
import com.example.appnghenhac.Main.DanhSachBaiHatActivity
import com.example.appnghenhac.Model.ChuDe
import com.example.appnghenhac.Model.TheLoai
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_chu_de.view.*
import java.net.URL

class TheLoaiAdapter (val context: Context, val data:ArrayList<TheLoai>): RecyclerView.Adapter<TheLoaiAdapter.ViewHolder>(){
    class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        fun bind(theLoai: TheLoai, position: Int){

            Picasso.with(itemView.context).load(theLoai.hinhTheLoai).into(itemView.imgChuDe)
            itemView.txtTenChuDe.text= theLoai.tenTheLoai
//            val  inputStream= URL(theLoai.hinhTheLoai).openConnection().getInputStream()
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//
//            itemView.imgChuDe.setImageBitmap(ImageConverter().getRoundedCornerBitmap(bitmap,50))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheLoaiAdapter.ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_chu_de, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TheLoaiAdapter.ViewHolder, position: Int) {
        holder.bind(data[position],position)
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(holder.itemView.context, DanhSachBaiHatActivity::class.java)
            intent.putExtra("idptheloai",data[position])
            holder.itemView.context.startActivity(intent)
        }
    }

}