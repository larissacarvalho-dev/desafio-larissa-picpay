package com.picpay.desafio.android.data.repository

import android.util.Log
import com.picpay.desafio.android.data.model.local.UserDAO
import com.picpay.desafio.android.data.model.remote.User
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
  private val service: PicPayService,
  private val userDao: UserDAO
) {

  fun getUsers(): Single<List<User>> {
    return service.getUsers().flatMap(Function<List<User>, SingleSource<List<User>>> {
      if (it.isNotEmpty()) {
        return@Function Single.just(it)
      } else {
        return@Function Single.just(ArrayList<User>())
      }
    }).flatMap(this::saveUsers)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  private fun saveUsers(users: List<User>): Single<List<User>> {
    return Single.create(SingleOnSubscribe<List<User>> {
      val usersClone: MutableList<User> = ArrayList()
      for (user: User in users) {
        usersClone.add(user)
      }
      userDao.insertAllUsers(usersClone)
      it.onSuccess(users)
    }).doOnError { Log.e("DATA_BASE_ERROR: ", "Ocorreu um erro ao salvar os dados!", it) }
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.newThread())
  }

  fun getUserDataBase(): Single<List<User>> {
    return userDao.getAllUsers().doOnError {
      Log.e("DATA_BASE_ERROR: ",
        "Ocorreu um erro ao buscar os dados!", it)
    }.subscribeOn(Schedulers.io())
      .observeOn(Schedulers.newThread())
      .flatMap(Function<List<User>, SingleSource<List<User>>> {
        return@Function Single.just(it)
      })
  }
}