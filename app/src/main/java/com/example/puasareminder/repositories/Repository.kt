package id.co.booksapp.repositories

import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import com.example.puasareminder.repositories.remote.RemoteDataSource
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val remote: RemoteDataSource
): IRepository {
    override fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return remote.loginUser(email, password)
    }

    override fun registerUser(
        name: String,
        email: String,
        password: String
    ): Flow<ResponseState<Users>> {
        return remote.registerUser(name, email,password)
    }

    override fun getPuasa(): Flow<ResponseState<List<Puasa>>> {
        return remote.getPuasa()
    }


}