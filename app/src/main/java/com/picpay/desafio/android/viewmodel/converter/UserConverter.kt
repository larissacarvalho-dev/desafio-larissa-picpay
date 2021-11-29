package com.picpay.desafio.android.viewmodel.converter

import android.view.View
import android.view.View.GONE
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.remote.User
import com.picpay.desafio.android.viewmodel.presentation.ItemUserPresentation
import com.picpay.desafio.android.viewmodel.presentation.UserPresentation
import javax.inject.Inject

class UserConverter @Inject constructor() {

  fun convertUsers(users: List<User>): UserPresentation {
    val builder: UserPresentation.Builder = UserPresentation.Builder()

    if (users.isNotEmpty()) {
      builder.setItemUserPresentation(getUsers(users))
        .setTitle(R.string.title)
    } else {
      builder.setItemUserPresentation(ArrayList())
    }

    return builder.build()
  }

  private fun getUsers(users: List<User>): List<ItemUserPresentation> {
    val itemPresentationList: MutableList<ItemUserPresentation> = ArrayList()

    for (user: User in users) {
      itemPresentationList.add(getItemPresentation(user))
    }
    return itemPresentationList
  }

  private fun getItemPresentation(user: User): ItemUserPresentation {
    val builder: ItemUserPresentation.Builder = ItemUserPresentation.Builder()
    builder.setProgressBarVisible(View.VISIBLE)
      .setProgressBarGone(GONE)
      .setDefaultImg(R.drawable.ic_round_account_circle)
    if (user.name.trim().isNotEmpty() && user.username.trim().isNotEmpty() && user.img.trim().isNotEmpty()) {
      builder.setImg(user.img)
        .setTxtName(user.name)
        .setTxtUsername(user.username)
    } else {
      builder.setVisibilityItem(GONE)
    }
    return builder.build()
  }
}