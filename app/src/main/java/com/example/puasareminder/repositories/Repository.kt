package id.co.booksapp.repositories

import com.example.puasareminder.model.JenisPuasa
import com.example.puasareminder.model.Puasa
import com.example.puasareminder.model.Users
import com.example.puasareminder.repositories.local.LocalDataSource
import com.example.puasareminder.repositories.local.entity.PuasaEntity
import com.example.puasareminder.repositories.remote.RemoteDataSource
import id.co.booksapp.model.*
import id.co.booksapp.model.response.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
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

    override fun getJenisPuasa(): Flow<ResponseState<List<JenisPuasa>>> {
        return remote.getJenisPuasa()
    }

    override fun getUser(): Flow<ResponseState<Users>> {
        return remote.getUsersById()
    }

    override fun logoutUser(): Flow<ResponseState<Boolean>> {
        return remote.logoutUser()
    }

    override fun insertPuasa(puasaEntity: PuasaEntity): Long {
        return local.insertPuasa(puasaEntity)
    }

    override fun getPuasaByUser(idUser: String): List<PuasaEntity> {
        return local.getPuasaByUser(idUser)
    }

    override fun checkPuasaIsExists(idPuasa: String): Boolean {
        return local.checkPuasaIsExists(idPuasa)
    }

    override fun deleteItemPuasa(puasaEntity: PuasaEntity) {
        return local.deleteItemPuasa(puasaEntity)
    }


}