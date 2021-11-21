package com.picpay.desafio.android.data.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.model.remote.User

@Database(entities = [User::class], version = BuildConfig.VERSION_CODE, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun userDao(): UserDAO
}