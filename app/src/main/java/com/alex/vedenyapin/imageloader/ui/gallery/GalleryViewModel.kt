package com.alex.vedenyapin.imageloader.ui.gallery

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.base.BaseViewModel
import com.alex.vedenyapin.imageloader.model.Image
import com.alex.vedenyapin.imageloader.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
class GalleryViewModel: BaseViewModel() {

    @Inject
    lateinit var api: Api

    val imageGridAdapter: ImageGridAdapter = ImageGridAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = api.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveImageListStart() }
                .doOnTerminate { onRetrieveImageListFinish() }
                .subscribe(
                        { result -> onRetrieveImageListSuccess(result) },
                        { onRetrieveImageListError() }
                )
    }

    private fun onRetrieveImageListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveImageListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveImageListSuccess(imageList:List<Image>){
        imageGridAdapter.updateImageList(imageList)
    }

    private fun onRetrieveImageListError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}