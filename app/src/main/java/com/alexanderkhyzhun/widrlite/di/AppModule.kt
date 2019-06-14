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
import com.alexanderkhyzhun.widrlite.data.storage.StorageRepository
import com.alexanderkhyzhun.widrlite.data.storage.impl.StorageRepositoryImpl
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


        /////////////////////
        // UseCases
        ////////////////////
//        single { ProfileUseCaseImpl() } bind (ProfileUseCase::class)
//        single { SplashUseCaseImpl(get()) } bind (SplashUseCase::class)
//        single { SignUpPersonalUseCaseImpl(get(), get()) } bind (SignUpPersonalUseCase::class)
//        single { SignUpCustomerUseCaseImpl(get(), get()) } bind (SignUpCustomerUseCase::class)
//        single { SignUpBusinessUseCaseImpl(get(), get()) } bind (SignUpBusinessUseCase::class)
//        single { ProfessionUseCaseImpl(get(), get()) } bind (ProfessionUseCase::class)
//        single { ServicesUseCaseImpl(get(), get()) } bind (ServicesUseCase::class)
//        single { ConfirmationUseCaseImpl(get()) } bind (ConfirmationUseCase::class)

    }

    companion object {

        fun getLogger() = when (BuildConfig.DEBUG) {
            true -> AndroidLogger()
            false -> EmptyLogger()
        }
    }

}