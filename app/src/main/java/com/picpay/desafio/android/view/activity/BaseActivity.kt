package com.picpay.desafio.android.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.picpay.desafio.android.view.RxManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
  HasAndroidInjector {

  protected lateinit var viewBinding: VB

  @Inject
  lateinit var rxManager: RxManager

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

  final override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    daggerConfiguration()
    viewBinding = createViewBinding()
    setContentView(viewBinding.root)
  }

  @JvmOverloads
  fun <T> callAction(
    observable: Observable<T>, onSucess: Consumer<in T>? = null, onError: Consumer<Throwable>? = null
  ): Observable<T> {
    return rxManager.callAction(observable, onSucess, onError)
  }

  override fun onDestroy() {
    rxManager.onClear()
    super.onDestroy()
  }

  override fun onResume() {
    super.onResume()
  }

  override fun onStart() {
    super.onStart()
  }

  override fun onPause() {
    super.onPause()
  }

  override fun onBackPressed() {
    super.onBackPressed()
  }

  protected open fun daggerConfiguration() {
    AndroidInjection.inject(this)
  }

  abstract fun createViewBinding(): VB
}