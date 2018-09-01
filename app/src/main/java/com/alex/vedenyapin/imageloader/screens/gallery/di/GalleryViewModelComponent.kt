package com.alex.vedenyapin.imageloader.screens.gallery.di

import com.alex.vedenyapin.imageloader.di.di.NetworkModule
import com.alex.vedenyapin.imageloader.screens.gallery.ui.GalleryViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
@Singleton
@Component(modules = [(NetworkModule::class), (GalleryModule::class)])
interface GalleryViewModelComponent {

    fun inject(postListViewModel: GalleryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): GalleryViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder

        fun galleryModule(galleryModule: GalleryModule): Builder
    }
}