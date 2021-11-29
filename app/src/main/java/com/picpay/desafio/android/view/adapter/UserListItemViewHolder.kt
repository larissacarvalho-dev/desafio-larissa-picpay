package com.picpay.desafio.android.view.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.picpay.desafio.android.constants.UserConstants
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.viewmodel.presentation.ItemUserPresentation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
  private var viewBinding: ViewBinding,
  private val itemBinding: ListItemUserBinding = viewBinding as ListItemUserBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

  fun bind(itemUserPresentation: ItemUserPresentation) {
    itemBinding.name.text = itemUserPresentation.txtName
    itemBinding.username.text = itemUserPresentation.txtUsername
    itemBinding.progressBar.visibility = itemUserPresentation.progressBarVisible
    itemBinding.name.visibility = itemUserPresentation.visibilityItem
    itemBinding.username.visibility = itemUserPresentation.visibilityItem
    itemBinding.picture.visibility = itemUserPresentation.visibilityItem

    Picasso.get()
      .load(img(itemUserPresentation))
      .error(itemUserPresentation.defaultImg)
      .into(itemBinding.picture, object : Callback {
        override fun onSuccess() {
          itemView.progressBar.visibility = itemUserPresentation.progressBarGone
        }

        override fun onError(e: Exception?) {
          if (e != null) {
            Log.e(UserConstants.Picasso.ERROR_PICASSO, UserConstants.Picasso.MESSAGE_ERROR_PICASSO, e)
          }
          itemView.progressBar.visibility = itemUserPresentation.progressBarGone
        }
      })
  }

  private fun img(itemUserPresentation: ItemUserPresentation): String {
    var img = itemUserPresentation.img
    if (img.isEmpty()) {
      img = "https://randomuser.me/api/portraits/men/1.jpg"
    }
    return img
  }
}