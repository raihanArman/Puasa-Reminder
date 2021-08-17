package com.example.puasareminder.repositories.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.puasareminder.model.Puasa


@Entity(tableName = "tb_puasa")
data class PuasaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0,

    @ColumnInfo(name = "id_user")
    var idUser: String ?= "",

    @ColumnInfo(name = "id_puasa")
    var idPuasa: String ?= "",

    var puasa: Puasa

)