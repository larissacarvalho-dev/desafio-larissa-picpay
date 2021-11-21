package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.data.model.local.AppDatabase
import com.picpay.desafio.android.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class AppController : DaggerApplication() {

  private lateinit var mAppDatabase: AppDatabase

  override fun onCreate() {
    super.onCreate()
    initializeRoomDataBase()
  }

  private val applicationInjector = DaggerAppComponent.builder()
    .application(this)
    .build()

  override fun applicationInjector() = applicationInjector

  private fun initializeRoomDataBase() {
    mAppDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "picpay.users")
      .fallbackToDestructiveMigration()
      .build()
  }

  fun getDataBase(): AppDatabase{
    return mAppDatabase
  }
}