package com.example.appnghenhac.Fragment.Personal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Adapter.BannerAdapter
import com.example.appnghenhac.Adapter.PlayListAdapter
import com.example.appnghenhac.Model.PlayList
import com.example.appnghenhac.Model.QuangCao
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.main.fragment_banner.*
import kotlinx.android.synthetic.main.fragment_playlist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.app.ActionBar
import android.content.Intent
import android.widget.Toast
import com.example.appnghenhac.R
import android.widget.AdapterView
import com.example.appnghenhac.Main.DanhSachPlayListActivy


class PlayListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        //get UI

        getData()
        return view
    }

    private fun getData() {
        val dataService: DataService = ApiService.getService()
        val callback = dataService.getDataPlayList()
        callback.enqueue(object : Callback<List<PlayList>> {
            override fun onResponse(call: Call<List<PlayList>>, response: Response<List<PlayList>>) {
                var dataplaylist = response.body() as ArrayList
                Log.d("Load APT playlist", "${dataplaylist[0].ten}")
                lvPlayList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                lvPlayList.isNestedScrollingEnabled = true //
//               lvPlayList.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, 100))
                val adapter= PlayListAdapter(context!!, dataplaylist)
                lvPlayList.adapter =adapter
                txtMorePlayList.setOnClickListener {
                    val intent1= Intent(context, DanhSachPlayListActivy::class.java)
                    startActivity(intent1)
                }

            }

            override fun onFailure(call: Call<List<PlayList>>, t: Throwable) {
                Log.d("load API", "fail")
            }
        })

    }

}