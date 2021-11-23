package com.picpay.desafio.android.viewmodel.presentation

class UserPresentation(val txtTitle: Int, val itemUserPresentation: List<ItemUserPresentation>) {

  class Builder {
    private var itemUserPresentation: List<ItemUserPresentation> = ArrayList()
    private var txtTitle = 0

    fun setTitle(txtTitle: Int): Builder {
      this.txtTitle = txtTitle
      return this
    }

    fun setItemUserPresentation(value: List<ItemUserPresentation>): Builder {
      this.itemUserPresentation = value
      return this
    }

    fun build(): UserPresentation = UserPresentation(txtTitle, itemUserPresentation)
  }
}