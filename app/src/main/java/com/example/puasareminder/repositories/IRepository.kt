package id.co.booksapp.repositories

import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>
    fun registerUser(name: String, email: String, password: String): Flow<ResponseState<Users>>
    fun getPuasa(): Flow<ResponseState<List<Puasa>>>
}