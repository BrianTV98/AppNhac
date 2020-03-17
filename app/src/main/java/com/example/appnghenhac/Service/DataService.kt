package com.example.appnghenhac.Service

import com.example.appnghenhac.Model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DataService {
    @GET("songbanner.php")
    fun getDataBanner(): Call<List<QuangCao>>

    @GET("playlistforcurrent.php")
    fun getDataPlayList(): Call<List<PlayList>>

    @GET("chudevatheloai.php")
    fun getDataTheLoai(): Call<List<TheLoai>>

    @GET("albumHot.php")
    fun getDataAlbum(): Call<List<Album>>

    @GET("BaiHat.php")
    fun getDataBaiHat(): Call<List<BaiHat>>

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    fun getDanhSachBaiHatTheoQuangCao(@Field("idquangcao") idquangcao:String?) :Call<List<BaiHat>>

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    fun getDanhSachBaiHattheoTheLoai(@Field("idtheloai") idtheloai:String?) :Call<List<BaiHat>>

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    fun getDanhSachBaiHattheoPlayList(@Field("idplaylist") idplaylist:String?) :Call<List<BaiHat>>

    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    fun getDanhSachBaiHattheoAlbum(@Field("idAlBum") idAlbum: String?): Call<List<BaiHat>>

    @GET("danhSachTatCaPlayList.php")
    fun getDanhSachPhayList(): Call<List<PlayList>>

    @GET("DanhSachTatCaTheLoai.php")
    fun getDanhSachTheLoai(): Call<List<TheLoai>>

    @GET("danhSachTatCaAlbum.php")
    fun getDanhSachAlbum(): Call<List<Album>>




}