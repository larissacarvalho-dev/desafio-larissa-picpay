package com.picpay.desafio.android.infra;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@Documented
@MapKey
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
  //você pode usar qualquer classe desde que ela herde as caracteristicas de viewModel
  // (explicação da implementação)
  Class<? extends ViewModel> value();
}
