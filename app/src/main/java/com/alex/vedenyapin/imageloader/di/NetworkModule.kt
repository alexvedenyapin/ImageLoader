package com.alex.vedenyapin.imageloader.di.di

import com.alex.vedenyapin.imageloader.network.Api
import com.alex.vedenyapin.imageloader.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */

/**
 * Module which provides all required dependencies about network
 */
@Module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Image service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Image service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}