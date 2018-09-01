package com.alex.vedenyapin.imageloader.di.modules

import com.alex.vedenyapin.imageloader.domain.GalleryInteractor
import com.alex.vedenyapin.imageloader.network.Api
import dagger.Module
import dagger.Provides

/**
 * Created by Alex Vedenyapin on 01.09.2018
 */
@Module
class GalleryModule {

    @Provides
    fun provideGalleryInteractor(api: Api): GalleryInteractor {
        return GalleryInteractor(api)
    }
}