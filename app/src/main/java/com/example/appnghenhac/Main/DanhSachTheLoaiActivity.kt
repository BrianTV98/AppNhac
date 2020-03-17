package com.example.appnghenhac.Main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Adapter.DanhSachPlayListAdapter
import com.example.appnghenhac.Adapter.DanhSachTheLoaiAdapter
import com.example.appnghenhac.Adapter.TheLoaiAdapter
import com.example.appnghenhac.Model.PlayList
import com.example.appnghenhac.Model.TheLoai
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.main.activity_danh_sach_play_list_activy.*

import kotlinx.android.synthetic.main.activity_danh_sach_the_loai.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DanhSachTheLoaiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_the_loai)
        getData()
        init()
    }
    private fun getData() {
        val dataService: DataService = ApiService.getService()
        val callback=dataService.getDanhSachTheLoai()
        callback.enqueue(object : Callback<List<TheLoai>> {
            override fun onFailure(call: Call<List<TheLoai>>, t: Throwable) {

            }
            override fun onResponse(call: Call<List<TheLoai>>, response: Response<List<TheLoai>>) {
                val data = response.body() as ArrayList
                rcvTheLoai.layoutManager= LinearLayoutManager(this@DanhSachTheLoaiActivity, RecyclerView.VERTICAL,false)
                rcvTheLoai.adapter= DanhSachTheLoaiAdapter(this@DanhSachTheLoaiActivity,data)
            }
        })
    }

    private fun init() {
        setSupportActionBar(toobarAllTheLoai)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Tất Cả Thể Loai")
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
