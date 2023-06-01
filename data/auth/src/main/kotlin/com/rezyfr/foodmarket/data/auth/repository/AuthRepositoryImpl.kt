package com.rezyfr.foodmarket.data.auth.repository

import androidx.datastore.preferences.core.edit
import com.rezyfr.foodmarket.core.network.util.handleResponse
import com.rezyfr.foodmarket.core.persistence.datastore.DataStoreKeys
import com.rezyfr.foodmarket.core.persistence.datastore.FmDataStore
import com.rezyfr.foodmarket.data.auth.AuthService
import com.rezyfr.foodmarket.data.auth.model.SignInRequest.Companion.fromSigninParams
import com.rezyfr.foodmarket.data.auth.model.SignUpRequest.Companion.fromSignupParams
import com.rezyfr.foodmarket.domain.auth.model.SignInParams
import com.rezyfr.foodmarket.domain.auth.model.SignInResult
import com.rezyfr.foodmarket.domain.auth.model.SignUpParams
import com.rezyfr.foodmarket.domain.auth.model.SignUpResult
import com.rezyfr.foodmarket.domain.auth.model.UserDomainModel
import com.rezyfr.foodmarket.domain.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dataStore: FmDataStore,
    private val dataStoreKeys: DataStoreKeys
) : AuthRepository {
    override suspend fun register(param: SignUpParams): SignUpResult {
        val response = authService.register(param.fromSignupParams()).handleResponse()
        return response!!.mapToSignUpResult()
    }

    override suspend fun login(param: SignInParams): SignInResult {
        val response = authService.login(param.fromSigninParams()).handleResponse()
        return response!!.mapToSignInResult()
    }

    override suspend fun saveToken(token: String) {
        dataStore.put(dataStoreKeys.token, token)
    }

    override fun getToken(): Flow<String?> {
        return dataStore.get(dataStoreKeys.token)
    }

    override suspend fun saveUser(user: UserDomainModel) {
        dataStore.dataStore.edit { pref ->
            pref[dataStoreKeys.userEmail] = user.email
            pref[dataStoreKeys.userId] = user.id.toString()
            pref[dataStoreKeys.userName] = user.name
            pref[dataStoreKeys.userPhoto] = user.profilePhotoUrl.ifEmpty { user.profilePhotoPath ?: "" }
        }
    }

    override fun getUser(): Flow<UserDomainModel?> {
        return dataStore.dataStore.data.map {
            UserDomainModel(
                id = it[dataStoreKeys.userId]?.toInt() ?: 0,
                email = it[dataStoreKeys.userEmail] ?: "",
                name = it[dataStoreKeys.userName] ?: "",
                profilePhotoPath = it[dataStoreKeys.userPhoto] ?: "",
                profilePhotoUrl = it[dataStoreKeys.userPhoto] ?: "",
            )
        }
    }
}