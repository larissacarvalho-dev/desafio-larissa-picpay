package com.picpay.desafio.android.di.module

import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.view.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module(includes = [UserViewModelModule::class])
abstract class UserModule {

  @Binds
  abstract fun appCompatActivity(mainActivity: MainActivity): AppCompatActivity
}