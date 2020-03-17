package com.example.appnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChuDe(
        @SerializedName("IdChuDe")
        @Expose
        var idChuDe: String,
        @SerializedName("TenChuDe")
        @Expose
        var tenChuDe: String,
        @SerializedName("HinhChuDe")
        @Expose
        var hinhChuDe: String
)