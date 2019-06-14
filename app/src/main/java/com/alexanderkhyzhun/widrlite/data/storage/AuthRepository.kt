package com.alexanderkhyzhun.widrlite.data.storage

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
interface AuthRepository {

    fun logout()
    fun isUserAuthorized(): Boolean

}