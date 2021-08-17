package com.example.puasareminder.request

import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import id.co.core.data.response.ResponseLogin
import id.co.core.data.response.Wrapper
import retrofit2.http.*

interface ApiRequest {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Wrapper<ResponseLogin>


    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Wrapper<ResponseLogin>

    @GET("puasa")
    suspend fun getPuasa(
        @Header("Authorization") token: String
    ): Wrapper<List<Puasa>>

    @GET("jenis")
    suspend fun getJenisPuasa(
        @Header("Authorization") token: String
    ): Wrapper<List<JenisPuasa>>

    @GET("user")
    suspend fun getUsersById(
        @Header("Authorization") token: String
    ): Wrapper<Users>


    @POST("logout")
    suspend fun logoutUser(
        @Header("Authorization") token: String,
    ): Wrapper<Boolean>

}