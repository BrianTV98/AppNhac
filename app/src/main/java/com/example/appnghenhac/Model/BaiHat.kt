package com.example.appnghenhac.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaiHat(
        @SerializedName("idBaiHat")
        @Expose
        var idBaiHat: String?,
        @SerializedName("idAlBum")
        @Expose
        var idAlBum: String?,
        @SerializedName("idTheLoai")
        @Expose
        var idTheLoai: String?,
        @SerializedName("idPhayList")
        @Expose
        var idPhayList: String?,
        @SerializedName("tenBaiHat")
        @Expose
        var tenBaiHat: String?,
        @SerializedName("hinhBaiHat")
        @Expose
        var hinhBaiHat: String?,
        @SerializedName("caSi")
        @Expose
        var caSi: String?,
        @SerializedName("linkBaiHat")
        @Expose
        var linkBaiHat: String?,
        @SerializedName("luocThich")
        @Expose
        var luocThich: String?
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(idBaiHat)
                parcel.writeString(idAlBum)
                parcel.writeString(idTheLoai)
                parcel.writeString(idPhayList)
                parcel.writeString(tenBaiHat)
                parcel.writeString(hinhBaiHat)
                parcel.writeString(caSi)
                parcel.writeString(linkBaiHat)
                parcel.writeString(luocThich)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<BaiHat> {
                override fun createFromParcel(parcel: Parcel): BaiHat {
                        return BaiHat(parcel)
                }

                override fun newArray(size: Int): Array<BaiHat?> {
                        return arrayOfNulls(size)
                }
        }
}