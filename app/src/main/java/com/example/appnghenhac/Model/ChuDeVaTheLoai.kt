package com.example.appnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChuDeVaTheLoai(
//        @SerializedName("TheLoaiFragment")
//        @Expose
//        var theLoai: List<TheLoai>?= null,
        @SerializedName("ChuDe")
        @Expose
        var chuDe: List<ChuDe>? = null
)