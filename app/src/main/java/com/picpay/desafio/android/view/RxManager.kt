package com.picpay.desafio.android.view

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class RxManager @Inject constructor() {

  private var disposable: CompositeDisposable = CompositeDisposable()

  fun <T> callAction(observable: Observable<T>, onSucess: Consumer<in T>?, onError: Consumer<Throwable>?):
      Observable<T> {
    disposable.add(observable.subscribe(onSucess, getThrowableForErrorUi(onError!!)))
    return observable
  }

  private fun getThrowableForErrorUi(onError: Consumer<Throwable>): Consumer<in Throwable> {
    return Consumer { throwable ->
      onError.accept(throwable)
    }
  }

  fun onClear() {
    disposable.clear()
  }
}