package com.example.puasareminder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import com.example.puasareminder.repositories.local.entity.PuasaEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import id.co.booksapp.model.response.ResponseState
import id.co.booksapp.repositories.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    fun getPuasa(): LiveData<ResponseState<List<Puasa>>>{
        return repository.getPuasa().asLiveData()
    }
    fun getJenisPuasa(): LiveData<ResponseState<List<JenisPuasa>>>{
        return repository.getJenisPuasa().asLiveData()
    }

    fun getUser(): LiveData<ResponseState<Users>>{
        return repository.getUser().asLiveData()
    }

    fun logoutUser(): LiveData<ResponseState<Boolean>>{
        return repository.logoutUser().asLiveData()
    }

    fun insertPuasa(puasaEntity: PuasaEntity){
        if(repository.checkPuasaIsExists(puasaEntity.idPuasa!!)){
            Log.d("KevinMantap", "insertPuasa: insert berhasil")
            repository.deleteItemPuasa(puasaEntity)
        }else{
            Log.d("KevinMantap", "insertPuasa: delete berhasil")
            repository.insertPuasa(puasaEntity)
        }
    }

    fun getPuasaByUser(idUser: String): List<PuasaEntity>{
        return repository.getPuasaByUser(idUser)
    }

    fun checkPuasaExists(idPuasa: String): Boolean{
        return repository.checkPuasaIsExists(idPuasa)
    }

}