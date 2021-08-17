package com.example.puasareminder.repositories.local.dao

import androidx.room.*
import com.example.puasareminder.repositories.local.entity.PuasaEntity

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPuasa(puasaEntity: PuasaEntity): Long


    @Query("SELECT * FROM tb_puasa WHERE id_user = :idUser")
    fun getPuasaByUser(idUser: String): List<PuasaEntity>

    @Query("SELECT * FROM tb_puasa WHERE id_puasa = :id")
    fun getPuasaById(id: String): PuasaEntity

    @Query("SELECT EXISTS (SELECT * FROM tb_puasa WHERE id_puasa = :id)")
    fun puasaIsExist(id: String): Boolean

    @Delete
    fun deleteItemPuasa(puasaEntity: PuasaEntity)

}