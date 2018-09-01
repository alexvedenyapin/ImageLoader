package com.alex.vedenyapin.imageloader.ui.gallery

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.di.components.DaggerGalleryViewModelComponent
import com.alex.vedenyapin.imageloader.di.components.GalleryViewModelComponent
import com.alex.vedenyapin.imageloader.di.modules.GalleryModule
import com.alex.vedenyapin.imageloader.di.modules.NetworkModule
import com.alex.vedenyapin.imageloader.domain.GalleryInteractor
import com.alex.vedenyapin.imageloader.model.Image
import javax.inject.Inject

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
class GalleryViewModel : ViewModel() {

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
        galleryInteractor.loadImages({ onStart() }, { onFinish() }, { onSuccess(it) }, { onError() })
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