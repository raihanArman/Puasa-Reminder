package com.example.puasareminder.repositories.remote

import android.util.Log
import com.example.puasareminder.datastore.UserDataStore
import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import com.example.puasareminder.request.ApiRequest
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiRequest,
    private val userDataStore: UserDataStore
) {

    fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.loginUser(email, password)
                if(response.meta!!.code == 200){
                    val data = response.data!!.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.data.accessToken)
                    Log.d("Mantap", "loginUser sukses : value ${response.meta.message}")
                }else{
                    Log.d("Mantap", "loginUser error : value ${response.meta.message}")
                    emit(ResponseState.Error(response.meta.message))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun registerUser(name: String, email: String,  password: String): Flow<ResponseState<Users>> {
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.registerUser(name, email,password)
                if(response.meta!!.code == 200){
                    val data = response.data!!.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.data.accessToken)
                    Log.d("Mantap", "registerUser sukses : value ${response.meta.message}")
                }else{
                    Log.d("Mantap", "registerUser error : value ${response.meta.message}")
                    emit(ResponseState.Error(response.meta.message))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPuasa(): Flow<ResponseState<List<Puasa>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getPuasa(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }
    fun getJenisPuasa(): Flow<ResponseState<List<JenisPuasa>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getJenisPuasa(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getUsersById(): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getUsersById(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun logoutUser(): Flow<ResponseState<Boolean>> {
        return flow {
            emit(ResponseState.Loading())
            try {
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.logoutUser(token = "Bearer $it")
                    val data = response.data
                    if (response.meta!!.code == 200) {

                        if (data != null) {
                            if(data) {
                                userDataStore.storeStatusLogin(false)
                                userDataStore.storeTokenUser("")
                                userDataStore.storeUser("")
                                emit(ResponseState.Success(data))
                            }else{
                                emit(ResponseState.Error("Logout error"))
                            }
                        } else {
                            emit(ResponseState.Empty)
                        }
                    } else {
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            } catch (e: Exception) {
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}