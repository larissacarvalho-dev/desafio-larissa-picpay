package com.picpay.desafio.android.viewmodel.presentation

class ItemUserPresentation(
  val img: String,
  val txtName: String,
  val txtUsername: String,
  val progressBarVisible: Int,
  val progressBarGone: Int,
  val defaultImg: Int,
  val visibilityItem: Int
) {

  class Builder {
    private var img = ""
    private var txtName = ""
    private var txtUsername = ""
    private var progressBarVisible = 0
    private var progressBarGone = 0
    private var visibilityItem = 0
    private var defaultImg = 0

    fun setImg(img: String): Builder {
      this.img = img
      return this
    }

    fun setTxtName(txtName: String): Builder {
      this.txtName = txtName
      return this
    }

    fun setTxtUsername(txtUsername: String): Builder {
      this.txtUsername = txtUsername
      return this
    }

    fun setProgressBarVisible(progressBarVisible: Int): Builder {
      this.progressBarVisible = progressBarVisible
      return this
    }

    fun setProgressBarGone(progressBarGone: Int): Builder {
      this.progressBarGone = progressBarGone
      return this
    }

    fun setDefaultImg(defaultImg: Int): Builder {
      this.defaultImg = defaultImg
      return this
    }

    fun setVisibilityItem(visibilityItem: Int): Builder {
      this.visibilityItem = visibilityItem
      return this
    }

    fun build(): ItemUserPresentation = ItemUserPresentation(img, txtName, txtUsername, progressBarVisible,
      progressBarGone, defaultImg, visibilityItem)
  }
}