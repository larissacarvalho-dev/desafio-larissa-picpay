package com.picpay.desafio.android.data.model.remote

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Int,
    @SerializedName("username")
    val username: String
) : Parcelable