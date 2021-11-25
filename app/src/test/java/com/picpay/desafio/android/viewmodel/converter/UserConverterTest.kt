package com.picpay.desafio.android.viewmodel.converter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.model.remote.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [User::class])
class UserConverterTest {

  private val user = mock<User>()

  private lateinit var userConverter: UserConverter

  companion object {
    const val ID = 1
    const val NAME = "name"
    const val USERNAME = "username"
    const val IMG = "img"
    const val TITLE = 0
  }

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    userConverter = UserConverter()
    whenever(user.id).thenReturn(ID)
    whenever(user.name).thenReturn(NAME)
    whenever(user.username).thenReturn(USERNAME)
    whenever(user.img).thenReturn(IMG)
  }

  @Test
  fun convertUsersTest() {
    //given
    val users = emptyList<User>()

    //then
    val result = userConverter.convertUsers(users)

    Assert.assertEquals(users, result.itemUserPresentation)
    Assert.assertEquals(TITLE, result.txtTitle)
  }
}