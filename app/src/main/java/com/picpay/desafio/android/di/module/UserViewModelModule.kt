package com.picpay.desafio.android.di.module

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.infra.ViewModelKey
import com.picpay.desafio.android.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(UserViewModel::class)
  abstract fun mainViewModel(mainViewModel: UserViewModel): ViewModel
}