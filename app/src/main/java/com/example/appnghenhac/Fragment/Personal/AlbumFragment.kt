package com.example.appnghenhac.Fragment.Personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Adapter.AlbumAdapter
import com.example.appnghenhac.Adapter.BannerAdapter
import com.example.appnghenhac.Adapter.DanhSachAlbumAdapter
import com.example.appnghenhac.Main.DanhSachAlbum
import com.example.appnghenhac.Main.DanhSachPlayListActivy
import com.example.appnghenhac.Main.DanhSachTheLoaiActivity
import com.example.appnghenhac.Model.Album
import com.example.appnghenhac.Model.QuangCao
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.main.fragment_album.*
import kotlinx.android.synthetic.main.fragment_banner.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AlbumFragment :Fragment(){
    private lateinit var rcview:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_album, container,false)
        rcview=view.findViewById(R.id.rcview)
        val txt=view.findViewById<TextView>(R.id.txtXemThemAlbum) // ko findViewById thi se bi dung app
        txt.setOnClickListener {
            val intent:Intent= Intent(context, DanhSachAlbum::class.java)
            startActivity(intent)
        }

        getData()
        return view
    }

    private fun getData() {
        val dataService:DataService=ApiService.getService()
        val callback=dataService.getDataAlbum()
        callback.enqueue(object : retrofit2.Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.d("load Album", "fail")
            }


            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                Log.d("load Album", "ok")
                var dataAlbum = response.body() as ArrayList
                rcview.run {
                    layoutManager= LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
                    adapter= AlbumAdapter(context,dataAlbum)

                }
            }

        })
    }
}