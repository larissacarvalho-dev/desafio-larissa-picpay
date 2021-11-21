package com.picpay.desafio.android.di.module

import com.picpay.desafio.android.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

  @ContributesAndroidInjector(modules = [UserModule::class])
  abstract fun mainActivity(): MainActivity
}