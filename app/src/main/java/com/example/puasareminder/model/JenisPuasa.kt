package com.example.puasareminder.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JenisPuasa(
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("nama")
    @Expose
    var nama: String,

    @SerializedName("keutamaan")
    @Expose
    var keutamaan: String,
): Parcelable