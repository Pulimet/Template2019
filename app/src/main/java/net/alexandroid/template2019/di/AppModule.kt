package net.alexandroid.template2019.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import net.alexandroid.template2019.TMDB_BASE_URL
import net.alexandroid.template2019.network.TmdbApiService
import net.alexandroid.template2019.ui.home.HomeViewModel
import net.alexandroid.template2019.ui.main.MainRepository
import net.alexandroid.template2019.ui.main.MainRepositoryImpl
import net.alexandroid.template2019.ui.main.MainViewModel
import net.alexandroid.template2019.ui.movie.MovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var appModule = module {
    single { Gson() }
    single { createOkHttpClient() }
    single { createWebService<TmdbApiService>(get(), TMDB_BASE_URL) }
    single<MainRepository> { MainRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { MovieViewModel() }
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

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    return retrofit.create(T::class.java)
}
