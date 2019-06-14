package com.alexanderkhyzhun.widrlite.data.storage.impl

import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.storage.AuthRepository
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class AuthRepositoryImpl(
    private val storage: StorageRepository,
    private val api: Api
): AuthRepository {


    override fun isUserAuthorized(): Boolean = storage.getAuthStatus()

    override fun logout() {
        storage.clear()
    }

}