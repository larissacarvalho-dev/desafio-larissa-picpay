package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.remote.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Single<List<User>>
}