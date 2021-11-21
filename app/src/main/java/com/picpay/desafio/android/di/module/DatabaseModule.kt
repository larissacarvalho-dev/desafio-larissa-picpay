package com.picpay.desafio.android.di.module

import android.app.Application
import com.picpay.desafio.android.data.model.local.AppDatabase
import com.picpay.desafio.android.data.model.local.UserDAO
import com.picpay.desafio.android.di.AppController
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return (application as AppController).getDataBase()
  }

  @Provides
  fun provideUserDao(appDatabase: AppDatabase): UserDAO {
    return appDatabase.userDao()
  }
}