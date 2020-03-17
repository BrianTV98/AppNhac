package com.example.appnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuangCao (
        @SerializedName("idQuangCao")
        @Expose
        var idQuangCao: String? = null,
        @SerializedName("HinhAnh")
        @Expose
        var hinhAnh: String? = null,
        @SerializedName("NoiDung")
        @Expose
        var noiDung: String? = null,
        @SerializedName("IdBaiHat")
        @Expose
        var idBaiHat: String? = null,
        @SerializedName("TenBaiHat")
        @Expose
        var tenBaiHat: String? = null,
        @SerializedName("HinhBaiHat")
        @Expose
        var hinhBaiHat: String? = null)
        :Serializable