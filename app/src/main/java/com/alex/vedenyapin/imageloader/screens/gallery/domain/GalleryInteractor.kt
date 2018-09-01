package com.alex.vedenyapin.imageloader.screens.gallery.domain

import com.alex.vedenyapin.imageloader.model.Image
import com.alex.vedenyapin.imageloader.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Vedenyapin on 01.09.2018
 */
class GalleryInteractor(private val api: Api) {
    private lateinit var subscription: Disposable

    fun loadImages(onStart: () -> Unit, onFinish: () -> Unit, onSuccess: (List<Image>) -> Unit, onError: () -> Unit) {
        subscription = api.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onStart() }
                .doOnTerminate { onFinish() }
                .subscribe(
                        { onSuccess(it) },
                        { onError() }
                )
    }

    fun dispose() {
        subscription.dispose()
    }
}