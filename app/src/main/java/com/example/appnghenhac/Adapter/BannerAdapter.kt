package com.example.appnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.appnghenhac.Main.DanhSachBaiHatActivity
import com.example.appnghenhac.Model.BaiHat
import com.example.appnghenhac.Model.QuangCao
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_danh_sach_bai_hat.*
import kotlinx.android.synthetic.main.banner_row.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class BannerAdapter (val context: Context, val arrayList: ArrayList<QuangCao>) :PagerAdapter(){
    lateinit var Arrdata : ArrayList<BaiHat>
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view= LayoutInflater.from(context).inflate(R.layout.banner_row,null)

        Picasso.with(context).load(arrayList.get(position).hinhAnh).into(view.imgBackgroudBanner)
        Picasso.with(context).load(arrayList.get(position).hinhBaiHat).into(view.imgViewPager)
        view.titlebannerBaihat.text=arrayList.get(position).tenBaiHat
        view.txtnoiDung.text=arrayList.get(position).noiDung
        container.addView(view)
        view.setOnClickListener{


            val intent:Intent= Intent(context, DanhSachBaiHatActivity::class.java)
            intent.putExtra("Banner",arrayList[position])
            context.startActivity(intent)
        }
        return view
    }

    private fun getData(idQuangCao: String?)  {
        val dataService: DataService = ApiService.getService()
        val callback = dataService.getDanhSachBaiHatTheoQuangCao(idQuangCao)

        Log.d("ID truyen", "$idQuangCao")
        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                Arrdata =response.body() as ArrayList<BaiHat>
            }
        })

//        return  data
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}