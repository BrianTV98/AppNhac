package com.example.appnghenhac.Fragment.Personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Adapter.TheLoaiAdapter
import com.example.appnghenhac.Main.DanhSachTheLoaiActivity
import com.example.appnghenhac.Model.TheLoai
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.main.fragment_chude_theloai.*
import kotlinx.android.synthetic.main.fragment_chude_theloai.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheLoaiFragment :Fragment(){
//    private lateinit var txtTitleChuDeVaTheLoai: TextView
//    private lateinit var txtXemThemChuDeVaTheLoai: TextView
//    private lateinit var hztScrollView: HorizontalScrollView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_chude_theloai,container,false)
        getData()
        view.txtXemThemTheLoai.setOnClickListener {
            val intent= Intent(context, DanhSachTheLoaiActivity::class.java)
            startActivity(intent)
        }
        return  view
    }

    private fun getData() {
        val dataService : DataService = ApiService.getService()
        val callback = dataService.getDataTheLoai()
        callback.enqueue(object: Callback<List<TheLoai>>{
            override fun onFailure(call: Call<List<TheLoai>>, t: Throwable) {
                Log.d("Chu De","fail")
            }

            override fun onResponse(call: Call<List<TheLoai>>, response: Response<List<TheLoai>>) {
                Log.d("Chu De","ok")
                val dataService :DataService= ApiService.getService()
                val callback=dataService.getDataTheLoai()
                callback.enqueue(object :Callback<List<TheLoai>> {
                    override fun onFailure(call: Call<List<TheLoai>>, t: Throwable) {
                        Log.d("load API", "fail")
                    }
                    override fun onResponse(call: Call<List<TheLoai>>, response: Response<List<TheLoai>>) {
                        var data = response.body() as ArrayList
                        recyclerviewChuDe.layoutManager= LinearLayoutManager(context,RecyclerView.HORIZONTAL, false)
                        recyclerviewChuDe.adapter= TheLoaiAdapter(context!!, data)
                    }
                })
            }
        })
    }
}