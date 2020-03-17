package com.example.appnghenhac.Fragment.Personal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Adapter.BaiHatYeuThichAdapter
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.main.fragment_bai_hat_yeu_thich.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BaiHatYeuThichFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_bai_hat_yeu_thich,container, false)
        getData()
        return  view
    }
    private fun getData() {
        val dataService: DataService = ApiService.getService()
        val callback = dataService.getDataBaiHat()
        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                var dataBaiHat = response.body() as ArrayList
               // Log.d("Load APT playlist", "${dataplaylist[0].ten}")
                rcvBaiHatYeuThich.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                rcvBaiHatYeuThich.isNestedScrollingEnabled =false //
//               lvPlayList.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, 100))
                rcvBaiHatYeuThich.adapter = BaiHatYeuThichAdapter(context!!, dataBaiHat)
                Log.d("load Bai Hat", "${dataBaiHat[0].tenBaiHat}")
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {
                Log.d("load Bai Hat", "fail")
            }
        })
    }
}