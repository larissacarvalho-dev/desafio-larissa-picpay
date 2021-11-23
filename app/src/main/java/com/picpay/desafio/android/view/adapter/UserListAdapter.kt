package com.picpay.desafio.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.listener.UserListDiffCallback
import com.picpay.desafio.android.viewmodel.presentation.ItemUserPresentation

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

  var users = emptyList<ItemUserPresentation>()
    set(value) {
      val result = DiffUtil.calculateDiff(
        UserListDiffCallback(
          field,
          value
        )
      )
      result.dispatchUpdatesTo(this)
      field = value
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
    val view = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return UserListItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
    holder.bind(users[position])
  }

  override fun getItemCount(): Int = users.size
}