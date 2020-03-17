package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Main.PlayMusicActivity
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_baihat_yeuthich.view.*

class BaiHatYeuThichAdapter(val context: Context, val dataBaiHat: ArrayList<BaiHat>) : RecyclerView.Adapter<BaiHatYeuThichAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_baihat_yeuthich, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataBaiHat.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(context).load(dataBaiHat.get(position).hinhBaiHat).into(holder.imgBaiHat)
        if(position<10){
            holder.txtMaBaiHat.text="0${position+1}"
        }

        else holder.txtTenBaiHat.text="$position"
        holder.txtTenBaiHat.text=dataBaiHat.get(position).tenBaiHat
        holder.txtTenCaSiBaiHat.text=dataBaiHat.get(position).caSi
        holder.itemView.setOnClickListener {
            val intent:Intent= Intent(context, PlayMusicActivity::class.java)
            intent.putExtra("BaiHat",dataBaiHat[position])
            context.startActivity(intent)
        }
        holder.btnLike.setOnClickListener {
            Toast.makeText(context,"Tinh nang chua phat trien",Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtMaBaiHat =view.findViewById<TextView>(R.id.txtMaBaiHat)
        val imgBaiHat = view.findViewById<ImageView>(R.id.imgBaiHat)
        val txtTenBaiHat=view.findViewById<TextView>(R.id.txtTenBaiHat)
        val txtTenCaSiBaiHat=view.findViewById<TextView>(R.id.txtTenCaSiBaiHat)
        val btnLike = view.findViewById<ImageButton>(R.id.imgLike)
    }

}
