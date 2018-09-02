package com.alex.vedenyapin.imageloader.screens.comments.domain

import com.alex.vedenyapin.imageloader.model.Image
import com.alex.vedenyapin.imageloader.model.ImageDao
import com.alex.vedenyapin.imageloader.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class CommentsInteractor(private val api: Api) {

    private lateinit var subscription: Disposable

    fun dispose() {
        subscription.dispose()
    }

    fun loadImage(imageDao: ImageDao, imageId: Int, onSuccess: (image: Image) -> Unit, onError: () -> Unit) {
        subscription = Observable.fromCallable { imageDao.getById(imageId) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onSuccess(it) },
                        { onError() }
                )
    }
}