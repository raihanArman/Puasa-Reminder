package id.co.core.data.response

import com.example.puasareminder.model.Users
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @Expose
    @SerializedName("access_token")
    val accessToken: String,

    @Expose
    @SerializedName("token_type")
    val tokenType: String,

    @Expose
    @SerializedName("user")
    val user: Users
)