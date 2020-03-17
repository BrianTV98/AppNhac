package com.example.appnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Album(
        @SerializedName("idAlbum")
        @Expose
        var idAlbum: String,
        @SerializedName("tenAlBum")
        @Expose
        var tenAlBum: String,
        @SerializedName("tenCaSiAlbum")
        @Expose
        var tenCaSiAlbum: String,
        @SerializedName("hinhAlBum")
        @Expose
        var hinhAlBum: String
): Serializable