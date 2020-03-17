package com.example.appnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlayList (
    @SerializedName("idPlaylist")
    @Expose
    var idPlaylist :String,
    @SerializedName("Ten")
    @Expose
    var  ten: String,
    @SerializedName("HinhPlayList")
    @Expose
    var hinhPlayList :String,
    @SerializedName("Icon")
    @Expose
    var icon :String
): Serializable