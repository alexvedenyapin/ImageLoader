package com.alex.vedenyapin.imageloader.screens.gallery.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.screens.gallery.di.GalleryViewModelComponent
import com.alex.vedenyapin.imageloader.screens.gallery.di.GalleryModule
import com.alex.vedenyapin.imageloader.di.di.NetworkModule
import com.alex.vedenyapin.imageloader.screens.gallery.domain.GalleryInteractor
import com.alex.vedenyapin.imageloader.model.image.Image
import com.alex.vedenyapin.imageloader.model.image.ImageDao
import com.alex.vedenyapin.imageloader.screens.gallery.di.DaggerGalleryViewModelComponent
import javax.inject.Inject

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
class GalleryViewModel(private val imageDao: ImageDao): ViewModel() {

    @Inject
    lateinit var galleryInteractor: GalleryInteractor

    private val galleryComponent: GalleryViewModelComponent = DaggerGalleryViewModelComponent
            .builder()
            .networkModule(NetworkModule)
            .galleryModule(GalleryModule())
            .build()

    val imageGridAdapter: ImageGridAdapter = ImageGridAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadImages() }

    init {
        galleryComponent.inject(this)
        loadImages()
    }

    private fun loadImages() {
        galleryInteractor.loadImages(imageDao, { onStart() }, { onFinish() }, { onSuccess(it) }, { onError() })
    }

    private fun onStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onSuccess(imageList: List<Image>) {
        imageGridAdapter.updateImageList(imageList)
    }

    private fun onError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        galleryInteractor.dispose()
    }
}