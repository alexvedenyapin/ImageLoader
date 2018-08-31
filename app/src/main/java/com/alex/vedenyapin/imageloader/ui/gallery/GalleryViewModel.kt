package com.alex.vedenyapin.imageloader.ui.gallery

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.alex.vedenyapin.imageloader.base.BaseViewModel
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
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
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
                        { onRetrieveImageListSuccess() },
                        { onRetrieveImageListError() }
                )
    }

    private fun onRetrieveImageListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveImageListFinish() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveImageListSuccess() {

    }

    private fun onRetrieveImageListError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}