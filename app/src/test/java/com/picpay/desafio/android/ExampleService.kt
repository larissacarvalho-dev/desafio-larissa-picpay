package com.picpay.desafio.android

import com.picpay.desafio.android.data.repository.PicPayService
import com.picpay.desafio.android.data.model.remote.User

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}