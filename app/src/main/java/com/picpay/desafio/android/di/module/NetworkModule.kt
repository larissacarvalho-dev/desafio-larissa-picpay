package com.picpay.desafio.android.di.module

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Named("baseUrl")
  fun provideBaseUrl(): String {
    return "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
  }

  @Provides
  @Singleton
  fun provideHttpCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
  }

  @Provides
  @Singleton
  fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
    return gsonBuilder.create()
  }

  @Provides
  @Singleton
  fun provideRetrofit(
    gson: Gson, cache: Cache, @Named("baseUrl") baseUrl: String
  ): Retrofit {
    val builder: OkHttpClient.Builder = OkHttpClient.Builder().cache(cache)
      .connectTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
    val okHttpClient: OkHttpClient = builder.build()

    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create(gson))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
      .baseUrl(baseUrl)
      .client(okHttpClient)
      .build()
  }

  @Provides
  fun provideActivity(): AppCompatActivity {
    return AppCompatActivity()
  }
}