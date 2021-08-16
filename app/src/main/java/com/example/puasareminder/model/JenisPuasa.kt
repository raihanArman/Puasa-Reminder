package com.example.puasareminder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JenisPuasa(
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("nama")
    @Expose
    var nama: String,
)