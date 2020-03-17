package com.example.appnghenhac.Fragment.Personal

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.appnghenhac.Adapter.BannerAdapter
import com.example.appnghenhac.Main.MainActivity
import com.example.appnghenhac.Model.QuangCao
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_banner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class BannerFragment :Fragment(){
    private lateinit var viewPagerQuangCao: ViewPager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_banner, container, false)
        viewPagerQuangCao=view!!.findViewById(R.id.viewPage)
        getData()
        return view
    }

    private fun getData() {
        val dataService:DataService=ApiService.getService()
        val callback=dataService.getDataBanner()
        callback.enqueue(object :Callback<List<QuangCao>> {
            override fun onFailure(call: Call<List<QuangCao>>, t: Throwable) {
                Log.d("load API", "fail")
            }


            override fun onResponse(call: Call<List<QuangCao>>, response: Response<List<QuangCao>>) {
                var banners = response.body() as ArrayList
                val bannerAdapter= BannerAdapter(context!!,banners)
                viewPagerQuangCao.adapter=bannerAdapter
                indicator.setViewPager(viewPagerQuangCao)
            }

        })
    }
}