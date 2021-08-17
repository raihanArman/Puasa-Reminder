package com.example.puasareminder.repositories.local

import com.example.puasareminder.model.Users
import com.example.puasareminder.repositories.local.dao.DatabaseDao
import com.example.puasareminder.repositories.local.entity.PuasaEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val databaseDao: DatabaseDao
) {

    fun insertPuasa(puasaEntity: PuasaEntity): Long{
        return databaseDao.insertPuasa(puasaEntity)
    }

    fun getPuasaByUser(idUser: String): List<PuasaEntity>{
        return databaseDao.getPuasaByUser(idUser)
    }

    fun checkPuasaIsExists(idPuasa: String): Boolean{
        return databaseDao.puasaIsExist(idPuasa)
    }

    fun deleteItemPuasa(puasaEntity: PuasaEntity){
        return databaseDao.deleteItemPuasa(puasaEntity)
    }

}