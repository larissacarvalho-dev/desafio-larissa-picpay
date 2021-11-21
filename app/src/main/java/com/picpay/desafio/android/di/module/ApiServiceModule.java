package com.picpay.desafio.android.di.module;

import com.picpay.desafio.android.data.repository.PicPayService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiServiceModule {

  @Provides
  PicPayService providesPicPayService(Retrofit retrofit) {
    return retrofit.create(PicPayService.class);
  }
}
