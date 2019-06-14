package com.alexanderkhyzhun.widrlite.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.alexanderkhyzhun.widrlite.BuildConfig
import com.alexanderkhyzhun.widrlite.data.Api
import com.alexanderkhyzhun.widrlite.data.Schedulers
import com.alexanderkhyzhun.widrlite.data.impl.ApiFactory
import com.alexanderkhyzhun.widrlite.data.impl.InterceptorFactory
import com.alexanderkhyzhun.widrlite.data.impl.OkHttpFactory
import com.alexanderkhyzhun.widrlite.data.impl.SchedulersImpl
import com.alexanderkhyzhun.widrlite.data.storage.AuthRepository
import com.alexanderkhyzhun.widrlite.data.storage.SignUpRepository
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository
import com.alexanderkhyzhun.widrlite.data.storage.impl.AuthRepositoryImpl
import com.alexanderkhyzhun.widrlite.data.storage.impl.SignUpRepositoryImpl
import com.alexanderkhyzhun.widrlite.data.storage.impl.StorageRepositoryImpl
import com.alexanderkhyzhun.widrlite.domain.*
import com.alexanderkhyzhun.widrlite.domain.impl.*
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.logger.AndroidLogger
import org.koin.log.EmptyLogger

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class AppModule(val context: Context) {

    val appModule = org.koin.dsl.module.module {

        /////////////////////
        //Tools
        ////////////////////
        single { PreferenceManager.getDefaultSharedPreferences(context) } bind (SharedPreferences::class)
        single { GsonBuilder().create() }
        single { OkHttpClient().newBuilder() }
        single { InterceptorFactory(get(), get()) }
        single { OkHttpFactory(get()) }
        single { get<OkHttpFactory>().buildClient().build() }
        single { ApiFactory().create(Api::class.java, BuildConfig.BASE_API_URL, get()) }
        single { SchedulersImpl() } bind (Schedulers::class)
        single { Glide.with(context) }


        /////////////////////
        // Repositories
        ////////////////////
        single { StorageRepositoryImpl(get()) } bind (StorageRepository::class)
        single { AuthRepositoryImpl(get(), get()) } bind (AuthRepository::class)
        single { SignUpRepositoryImpl(get()) } bind (SignUpRepository::class)

        /////////////////////
        // UseCases
        ////////////////////
        single { SplashUseCaseImpl() } bind (SplashUseCase::class)
        single { AuthUseCaseImpl(get(), get()) } bind (AuthUseCase::class)
        single { ProfileUseCaseImpl() } bind (ProfileUseCase::class)
        single { NotificationsUseCaseImpl() } bind (NotificationsUseCase::class)
        single { MessagesUseCaseImpl() } bind (MessagesUseCase::class)
        single { NewsUseCaseImpl() } bind (NewsUseCase::class)
        single { ChatUseCaseImpl() } bind (ChatUseCase::class)

    }

    companion object {

        fun getLogger() = when (BuildConfig.DEBUG) {
            true -> AndroidLogger()
            false -> EmptyLogger()
        }
    }

}