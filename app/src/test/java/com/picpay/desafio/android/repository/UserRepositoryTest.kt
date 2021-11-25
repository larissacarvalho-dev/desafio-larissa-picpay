package com.picpay.desafio.android.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.model.local.UserDAO
import com.picpay.desafio.android.data.model.remote.User
import com.picpay.desafio.android.data.repository.PicPayService
import com.picpay.desafio.android.data.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [PicPayService::class])
class UserRepositoryTest {

  private val picPayService: PicPayService = mock()

  private val userDao: UserDAO = mock()

  private lateinit var userRepository: UserRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    RxAndroidPlugins.reset()
    RxJavaPlugins.reset()
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    userRepository = UserRepository(picPayService, userDao)
  }

  @Test
  fun getUsers() {
    //given
    val users = emptyList<User>()

    //when
    whenever(picPayService.getUsers()).thenReturn(Single.just(users))
    userRepository.saveUsers(users)


    val testObserver = userRepository.getUsers().test()

    Mockito.verify(userDao, Mockito.times(1)).insertAllUsers(users)
    testObserver?.assertComplete()
    testObserver?.assertNoErrors()
    testObserver?.assertSubscribed()
    testObserver.dispose()
  }
}