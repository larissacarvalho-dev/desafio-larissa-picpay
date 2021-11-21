package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.model.remote.User
import com.picpay.desafio.android.data.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

  fun getUsers(): Single<List<User>> {
    return userRepository.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
  }
}