package com.example.puasareminder.request

import com.example.puasareminder.model.Puasa
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
}