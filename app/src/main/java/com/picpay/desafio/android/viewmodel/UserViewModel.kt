package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.model.remote.User
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.viewmodel.converter.UserConverter
import com.picpay.desafio.android.viewmodel.presentation.UserPresentation
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(
  private val userRepository: UserRepository,
  private val converter: UserConverter
) : ViewModel() {

  fun getUserPresentation(): Single<UserPresentation> {
    return userRepository.getUsers().flatMap(Function<List<User>, SingleSource<UserPresentation>> {
      return@Function Single.just(converter.convertUsers(it))
    }).subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}