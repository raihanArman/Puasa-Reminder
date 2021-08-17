package com.example.puasareminder.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Puasa(
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("id_jenis_puasa")
    @Expose
    var idJenisPuasa: String,

    @SerializedName("tanggal")
    @Expose
    var tanggal: Date,

    @SerializedName("jenis")
    @Expose
    var jenisPuasa: JenisPuasa,

    var status: String = ""
)