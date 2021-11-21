package com.picpay.desafio.android.data.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.model.remote.User
import io.reactivex.Single

@Dao
interface UserDAO {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAllUsers(users: List<User>)

  @Query("SELECT * FROM User ORDER BY id ASC")
  fun getAllUsers(): Single<List<User>>
}