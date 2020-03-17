package com.example.appnghenhac.Main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appnghenhac.Adapter.DanhSachAlbumAdapter
import com.example.appnghenhac.Adapter.DanhSachPlayListAdapter
import com.example.appnghenhac.Model.Album
import com.example.appnghenhac.Model.PlayList
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService

import kotlinx.android.synthetic.main.activity_danh_sach_album.*
import kotlinx.android.synthetic.main.activity_danh_sach_bai_hat.*
import kotlinx.android.synthetic.main.activity_danh_sach_play_list_activy.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DanhSachAlbum : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_album)
        init()
        getData()

    }
    private fun getData() {
        val dataService: DataService = ApiService.getService()
        val callback=dataService.getDanhSachAlbum()
        callback.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.d("load API", "fail")

            }


            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                var data = response.body() as ArrayList
                recyclerviewAllAlbum.layoutManager= GridLayoutManager(this@DanhSachAlbum, 2)
                recyclerviewAllAlbum.adapter= DanhSachAlbumAdapter(this@DanhSachAlbum,data)


            }

        })

    }

    private fun init() {
        setSupportActionBar(toolBarAllAlbum)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("ALL ALBUM")
        toolBarAllAlbum.setTitleTextColor(resources.getColor(R.color.purple))


    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

}
