package net.alexandroid.template2019.di

import com.google.gson.Gson
import net.alexandroid.template2019.ui.main.MainRepository
import net.alexandroid.template2019.ui.main.MainRepositoryImpl
import net.alexandroid.template2019.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

var appModule = module {
    single { Gson() }
    single { createOkHttpClient() }

    single<MainRepository> { MainRepositoryImpl() }
    viewModel { MainViewModel(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(OkHttpLogs())
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

