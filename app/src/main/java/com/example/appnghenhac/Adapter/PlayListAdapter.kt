package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Main.DanhSachBaiHatActivity
import com.example.appnghenhac.Main.DanhSachPlayListActivy
import com.example.appnghenhac.Model.PlayList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_playlist.view.*
import kotlinx.android.synthetic.main.layout_row_playlist.view.*
import com.example.appnghenhac.ImageConverter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.appnghenhac.R
import java.net.URL



class PlayListAdapter(val context: Context, val data: ArrayList<PlayList>) :
        RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_row_playlist, parent, false)
//
        return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Picasso.with(context).load(data.get(position).hinhPlayList).into(holder.imgbackgroudPlayList)
            Picasso.with(context).load(data.get(position).icon).into(holder.imgiconPlayList)
            holder.texView.text = data.get(position).ten

            //bo goc ngoai code
            val  inputStream= URL(data.get(position).hinhPlayList).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)

            holder.imgbackgroudPlayList.setImageBitmap(ImageConverter().getRoundedCornerBitmap(bitmap,20))
            holder.imgbackgroudPlayList.setOnClickListener {
                val intent =Intent(context, DanhSachBaiHatActivity::class.java)
                Log.d("posotion"," $position")
                Log.d("idPlayList ne","${data.get(position).idPlaylist}")
                intent.putExtra("idPlaylist",data.get(position))
                context.startActivity(intent)
            }

        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imgiconPlayList = view.findViewById<ImageView>(R.id.imgPlayList)
            val imgbackgroudPlayList = view.findViewById<ImageView>(R.id.imgBackgroudPlayList)
            val texView = view.findViewById<TextView>(R.id.txtView)
        }
    

    }

//    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View { // gan rowListView into ListView
//        val view=LayoutInflater.from(context).inflate(R.layout.layout_row_playlist,null)
//        Picasso.with(context).load(data[p0].hinhPlayList).into(view.imgBackgroudPlayList)
//        Picasso.with(context).load(data[p0].icon).into(view.imgPlayList)
//        view.txtView.text=data[p0].ten
//        return  view
//    }
//
//    override fun getItem(p0: Int): Any {
//        return data[p0]
//    }
//
//    override fun getItemId(p0: Int): Long {
//        return p0.toLong()
//    }
//
//    override fun getCount(): Int {
//        return data.size
//    }

