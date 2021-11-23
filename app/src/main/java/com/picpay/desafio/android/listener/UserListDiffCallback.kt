package com.picpay.desafio.android.listener

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.viewmodel.presentation.ItemUserPresentation

class UserListDiffCallback(
  private val oldList: List<ItemUserPresentation>,
  private val newList: List<ItemUserPresentation>
) : DiffUtil.Callback() {

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].txtUsername == newList[newItemPosition].txtUsername
  }

  override fun getOldListSize(): Int {
    return oldList.size
  }

  override fun getNewListSize(): Int {
    return newList.size
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return true
  }
}