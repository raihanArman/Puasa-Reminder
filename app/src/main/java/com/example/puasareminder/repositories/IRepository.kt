package id.co.booksapp.repositories

import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import com.example.puasareminder.repositories.local.entity.PuasaEntity
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>
    fun registerUser(name: String, email: String, password: String): Flow<ResponseState<Users>>
    fun getPuasa(): Flow<ResponseState<List<Puasa>>>
    fun getJenisPuasa(): Flow<ResponseState<List<JenisPuasa>>>
    fun getUser(): Flow<ResponseState<Users>>
    fun logoutUser(): Flow<ResponseState<Boolean>>

    fun insertPuasa(puasaEntity: PuasaEntity): Long
    fun getPuasaByUser(idUser: String): List<PuasaEntity>
    fun checkPuasaIsExists(idPuasa: String): Boolean
    fun deleteItemPuasa(puasaEntity: PuasaEntity)

}