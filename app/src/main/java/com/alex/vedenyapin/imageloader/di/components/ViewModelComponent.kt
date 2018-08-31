package com.alex.vedenyapin.imageloader.di.components

import com.alex.vedenyapin.imageloader.di.modules.NetworkModule
import com.alex.vedenyapin.imageloader.ui.gallery.GalleryViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelComponent {

    fun inject(postListViewModel: GalleryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}