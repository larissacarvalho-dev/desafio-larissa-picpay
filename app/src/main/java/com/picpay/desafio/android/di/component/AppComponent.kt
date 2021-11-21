package com.picpay.desafio.android.di.component

import android.app.Application
import com.picpay.desafio.android.di.AppController
import com.picpay.desafio.android.di.module.ActivityBindingsModule
import com.picpay.desafio.android.di.module.ApiServiceModule
import com.picpay.desafio.android.di.module.DatabaseModule
import com.picpay.desafio.android.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [
  AndroidInjectionModule::class,
  ApiServiceModule::class,
  NetworkModule::class,
  ActivityBindingsModule::class,
  DatabaseModule::class
])
interface AppComponent : AndroidInjector<AppController> {

  override fun inject(daggerApplication: AppController)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder
    fun build(): AppComponent
  }
}