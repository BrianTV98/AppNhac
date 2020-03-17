package com.example.appnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TheLoai(
        @SerializedName("IdTheLoai")
        @Expose
        var idTheLoai: String,
        @SerializedName("IdChuDe")
        @Expose
        var idChuDe: String,
        @SerializedName("TenTheLoai")
        @Expose
        var tenTheLoai: String,
        @SerializedName("HinhTheLoai")
        @Expose
        var hinhTheLoai: String
) :Serializable