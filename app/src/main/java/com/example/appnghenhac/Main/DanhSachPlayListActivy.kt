package com.example.appnghenhac.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appnghenhac.Adapter.BannerAdapter
import com.example.appnghenhac.Adapter.DanhSachPlayListAdapter
import com.example.appnghenhac.Model.PlayList
import com.example.appnghenhac.Model.QuangCao
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.main.activity_danh_sach_play_list_activy.*
import kotlinx.android.synthetic.main.fragment_banner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DanhSachPlayListActivy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_play_list_activy)
        init()
        getData()
    }

    private fun getData() {
        val dataService: DataService = ApiService.getService()
        val callback=dataService.getDanhSachPhayList()
        callback.enqueue(object : Callback<List<PlayList>> {
            override fun onFailure(call: Call<List<PlayList>>, t: Throwable) {
                Log.d("load API", "fail")

            }


            override fun onResponse(call: Call<List<PlayList>>, response: Response<List<PlayList>>) {
                var data = response.body() as ArrayList
                recyclerviewAllPlayList.layoutManager= GridLayoutManager(this@DanhSachPlayListActivy, 2)
                recyclerviewAllPlayList.adapter= DanhSachPlayListAdapter(this@DanhSachPlayListActivy,data)
            }

        })

    }

    private fun init() {
        setSupportActionBar(toolBarAllPlayList)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("PLAY LIST")
        toolBarAllPlayList.setTitleTextColor(resources.getColor(R.color.purple))

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
