package com.alex.vedenyapin.imageloader.base

import android.arch.lifecycle.ViewModel
import com.alex.vedenyapin.imageloader.di.components.ViewModelComponent
import com.alex.vedenyapin.imageloader.di.modules.NetworkModule
import com.alex.vedenyapin.imageloader.ui.gallery.GalleryViewModel

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
open class BaseViewModel: ViewModel() {
    private val mComponent: ViewModelComponent = DaggerViewModelComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is GalleryViewModel -> mComponent.inject(this)
        }
    }
}