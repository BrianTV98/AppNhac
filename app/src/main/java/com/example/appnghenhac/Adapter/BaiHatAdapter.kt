package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Main.PlayMusicActivity
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item_baihat.view.*

class BaiHatAdapter(val ctx: Context, val data: ArrayList<BaiHat>, val itemClick: (BaiHat) -> Unit)
    : RecyclerView.Adapter<BaiHatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(
            R.layout.layout_item_baihat,
            parent,
            false
        )
        return ViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value=data[position]
        holder.bindFeedModel( value , position)
    }

    class ViewHolder(view: View, val itemClick: (BaiHat) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindFeedModel(baiHat: BaiHat, position: Int) {
            with(baiHat) {
                itemView.setOnClickListener {
                    itemClick(this)
                    val intent= Intent(itemView.context,PlayMusicActivity::class.java)
                    intent.putExtra("BaiHat",baiHat)
                    itemView.context.startActivity(intent)
                }
                if(position<10){
                    itemView.txtMaBH.text= "0${position+1}"
                }
                else itemView.txtMaBH.text= "${position+1}"

                itemView.txtTenBH.text=tenBaiHat
                itemView.txtTenCaSiBH.text=caSi
                Picasso.with(itemView.context).load(hinhBaiHat).into(itemView.imgBH)
                itemView.imgLikeBH.setOnClickListener {
                    Toast.makeText(itemView.context,"Tính năng chưa phát triển",Toast.LENGTH_LONG).show()
                }

            }
        }
    }

}
