package com.example.appnghenhac.Main

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnghenhac.Adapter.BaiHatAdapter
import com.example.appnghenhac.Adapter.BaiHatYeuThichAdapter
import com.example.appnghenhac.Adapter.PlayListAdapter
import com.example.appnghenhac.R
import com.example.appnghenhac.Service.ApiService
import com.example.appnghenhac.Service.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_danh_sach_bai_hat.*
import kotlinx.android.synthetic.main.fragment_bai_hat.*
import kotlinx.android.synthetic.main.fragment_bai_hat_yeu_thich.*
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.fragment_playlist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.URI
import java.net.URL
import java.net.URLConnection
import android.view.View
import com.example.appnghenhac.Model.*


class DanhSachBaiHatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_bai_hat)
////
        //QuangCao
        try {
            Log.d("quangCaa000", "pass")
            val quangCao = intent.extras?.getSerializable("Banner") as QuangCao
            collapsing.title = quangCao.tenBaiHat
            setData(quangCao.tenBaiHat, quangCao)
            getData(quangCao.idQuangCao)
        } catch (e: Exception) {
        }

        //PlayList
        try {
            var playList = intent.extras?.getSerializable("idPlaylist") as PlayList
            collapsing.title = playList.ten
            setDataPlayList(playList.ten, playList.icon)
            getDataPlayList(playList.idPlaylist)
        } catch (f: Exception) { }
        try {
            val theLoai: TheLoai = intent.extras?.getSerializable("idptheloai") as TheLoai
            collapsing.title = theLoai.tenTheLoai
            setDataTheLoai(theLoai.tenTheLoai, theLoai.hinhTheLoai)
            getDataTheLoai(theLoai.idTheLoai)
        } catch (g: Exception) {
        }
        try {
            val album: Album = intent.extras?.getSerializable("idAlBum") as Album
            collapsing.title = album.tenAlBum
            setDataAlBum(album.tenAlBum, album.hinhAlBum)
            Log.d("tafd", "afdfa")
            getDataAlBum(album.idAlbum)
        } catch (e: Exception) {

        }

        init()
    }

    private fun getDataAlBum(idAlbum: String) {
        val dataService: DataService = ApiService.getService()
        val callback = dataService.getDanhSachBaiHattheoAlbum(idAlbum)

        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                val data = response.body() as ArrayList

                data.forEach { baihat -> d("List BH", "${baihat.tenBaiHat}") }
                rcvDanhSachBaiHat.layoutManager =
                    LinearLayoutManager(this@DanhSachBaiHatActivity, RecyclerView.VERTICAL, false)
                rcvDanhSachBaiHat.adapter = BaiHatAdapter(this@DanhSachBaiHatActivity, data, { itemClick ->
                    Unit
                    i("Ale", itemClick.tenBaiHat)
                })
                floatingActionButton.setOnClickListener {
                    val intent = Intent(this@DanhSachBaiHatActivity, PlayMusicActivity::class.java)
                    intent.putParcelableArrayListExtra("ArrayBaiHat",data)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {
                Log.d("DataAlbum", "fail")
            }
        })
    }

    private fun setDataAlBum(tenAlBum: String, hinhAlBum: String) {
        val urlHinh = URL(hinhAlBum)
        val bitmap = BitmapFactory.decodeStream(urlHinh.openConnection().getInputStream())
        val bitmapDrawable = BitmapDrawable(resources, bitmap)
        collapsing.background = bitmapDrawable
        Picasso.with(this).load(hinhAlBum).into(imagview_dscakhuc)
    }

    private fun getDataTheLoai(idTheLoai: String) {

        val dataService: DataService = ApiService.getService()
        Log.d("Gui", "$idTheLoai")
        val callback = dataService.getDanhSachBaiHattheoTheLoai(idTheLoai)
        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                var data = response.body() as ArrayList
                data.forEach { baihat -> d("List BH", "${baihat.tenBaiHat}") }
                rcvDanhSachBaiHat.layoutManager =
                    LinearLayoutManager(this@DanhSachBaiHatActivity, RecyclerView.VERTICAL, false)
                rcvDanhSachBaiHat.adapter = BaiHatAdapter(this@DanhSachBaiHatActivity, data, { itemClick ->
                    Unit
                    i("Ale", itemClick.tenBaiHat)
                })
                floatingActionButton.setOnClickListener {
                    val intent = Intent(this@DanhSachBaiHatActivity, PlayMusicActivity::class.java)
                    intent.putParcelableArrayListExtra("ArrayBaiHat",data)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {
                Log.d("baihatTheLoai", "fail")
            }
        })


    }

    private fun setDataTheLoai(tenTheLoai: String, hinhTheLoai: String) {
        val urlHinh = URL(hinhTheLoai)
        val bitmap = BitmapFactory.decodeStream(urlHinh.openConnection().getInputStream())
        val bitmapDrawable = BitmapDrawable(resources, bitmap)
        collapsing.background = bitmapDrawable
        Picasso.with(this).load(hinhTheLoai).into(imagview_dscakhuc)

    }

    private fun setDataPlayList(ten: String, hinhPlayList: String) {
        val urlHinh = URL(hinhPlayList)
        val bitmap = BitmapFactory.decodeStream(urlHinh.openConnection().getInputStream())
        val bitmapDrawable = BitmapDrawable(resources, bitmap)
        collapsing.background = bitmapDrawable
        Picasso.with(this).load(hinhPlayList).into(imagview_dscakhuc)

    }

    private fun getDataPlayList(idPlaylist: String) {
        val dataService: DataService = ApiService.getService()
        val callback = dataService.getDanhSachBaiHattheoPlayList(idPlaylist)


        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                var data = response.body() as ArrayList
                data.forEach { baihat -> d("List BH", "${baihat.tenBaiHat}") }
                rcvDanhSachBaiHat.layoutManager =
                    LinearLayoutManager(this@DanhSachBaiHatActivity, RecyclerView.VERTICAL, false)
//
                rcvDanhSachBaiHat.adapter = BaiHatAdapter(this@DanhSachBaiHatActivity, data, { itemClick ->
                    Unit
                    i("Ale", itemClick.tenBaiHat)
                })
                floatingActionButton.setOnClickListener {
                    val intent = Intent(this@DanhSachBaiHatActivity, PlayMusicActivity::class.java)
                    intent.putParcelableArrayListExtra("ArrayBaiHat",data)
                    startActivity(intent)
                }

            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {
                d("load API", "fail")
            }
        })
    }


    private fun getData(idQuangCao: String?) {
        val dataService: DataService = ApiService.getService()
        val callback = dataService.getDanhSachBaiHatTheoQuangCao(idQuangCao)
        d("ID truyen", "$idQuangCao")
        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                var data = response.body() as ArrayList
                data.forEach { baihat -> d("List BH", "${baihat.tenBaiHat}") }
                rcvDanhSachBaiHat.layoutManager =
                    LinearLayoutManager(this@DanhSachBaiHatActivity, RecyclerView.VERTICAL, false)
//                rcvDanhSachBaiHat.isNestedScrollingEnabled =false //
////               lvPlayList.setLayoutParams(ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, 100))
                rcvDanhSachBaiHat.adapter = BaiHatAdapter(this@DanhSachBaiHatActivity, data, { itemClick ->
                    Unit
                    i("Ale", itemClick.tenBaiHat)
                })
                floatingActionButton.setOnClickListener {
                    val intent = Intent(this@DanhSachBaiHatActivity, PlayMusicActivity::class.java)
                    intent.putParcelableArrayListExtra("ArrayBaiHat",data)
                    startActivity(intent)
                }

            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {
                d("load API", "fail")
            }
        })
    }

    private fun setData(name: String?, quangCao: QuangCao) {
        val urlHinh = URL(quangCao.hinhAnh)
        val bitmap = BitmapFactory.decodeStream(urlHinh.openConnection().getInputStream())
        val bitmapDrawable = BitmapDrawable(resources, bitmap)
        collapsing.background = bitmapDrawable
        Picasso.with(this).load(quangCao.hinhBaiHat).into(imagview_dscakhuc)
    }

    private fun init() {
        setSupportActionBar(toolBar) // bat chuc nang toolBar thay cho actionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // quay tro ve trang truoc

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing.setExpandedTitleColor(Color.WHITE) // mau khi mong
        collapsing.setCollapsedTitleTextColor(Color.WHITE) // set mau cho cho title
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
