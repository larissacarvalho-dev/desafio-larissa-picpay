package com.picpay.desafio.android.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.model.remote.User
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.viewmodel.converter.UserConverter
import com.picpay.desafio.android.viewmodel.presentation.UserPresentation
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [UserRepository::class, UserConverter::class])
class UserViewModelTest {

  private val userRepository: UserRepository = mock()

  private val converter: UserConverter = mock()

  private val userPresentation = UserPresentation(0, emptyList())

  private lateinit var userViewModel: UserViewModel

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    RxAndroidPlugins.reset()
    RxJavaPlugins.reset()
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    userViewModel = UserViewModel(userRepository, converter)
  }

  @Test
  fun getUserListTest() {
    //given
    val expectedUsers = emptyList<User>()
    userPresentation

    //when
    whenever(userRepository.getUsers()).thenReturn(Single.just(expectedUsers))
    whenever(converter.convertUsers(expectedUsers)).thenReturn(userPresentation)

    val testObserver = userViewModel.getUserPresentation().test()

    testObserver.assertValue(userPresentation)
    testObserver.assertSubscribed()
    testObserver.assertNoErrors()
    testObserver.assertComplete()
  }
}