package com.alex.vedenyapin.imageloader.screens.comments.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.di.di.NetworkModule
import com.alex.vedenyapin.imageloader.model.Image
import com.alex.vedenyapin.imageloader.model.ImageDao
import com.alex.vedenyapin.imageloader.screens.comments.di.CommentsModule
import com.alex.vedenyapin.imageloader.screens.comments.di.CommentsViewModelComponent
import com.alex.vedenyapin.imageloader.screens.comments.di.DaggerCommentsViewModelComponent
import com.alex.vedenyapin.imageloader.screens.comments.domain.CommentsInteractor
import javax.inject.Inject

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class CommentsViewModel(private val imageDao: ImageDao, private val imageId: Int): ViewModel() {

    @Inject
    lateinit var commentsInteractor: CommentsInteractor

    private val commentsComponent: CommentsViewModelComponent = DaggerCommentsViewModelComponent
            .builder()
            .networkModule(NetworkModule)
            .commentsModule(CommentsModule())
            .build()

//    val commentsAdapter: CommentsAdapter = CommentsAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val bigImage: MutableLiveData<Image> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadComments() }

    init {
        commentsComponent.inject(this)
        loadImage()
        loadComments()
    }

    private fun loadImage() {
        loadingVisibility.value = View.GONE
        commentsInteractor.loadImage(imageDao, imageId, { onImageLoadSuccess(it) }, { onImageLoadError() })
    }

    private fun onImageLoadSuccess(image: Image) {
        errorMessage.value = null
        bigImage.value = image
    }

    private fun onImageLoadError() {
        errorMessage.value = R.string.post_error
    }

    private fun loadComments() {
//        commentsInteractor.commentsDao(imageDao, { onStart() }, { onFinish() }, { onSuccess(it) }, { onError() })
    }

//    private fun onStart() {
//        loadingVisibility.value = View.VISIBLE
//        errorMessage.value = null
//    }
//
//    private fun onFinish() {
//        loadingVisibility.value = View.GONE
//    }
//
//    private fun onSuccess(commentList: List<Comment>) {
//        commentsAdapter.updateCommentsList(commentList)
//    }
//
//    private fun onError() {
//        errorMessage.value = R.string.post_error
//    }

    override fun onCleared() {
        super.onCleared()
        commentsInteractor.dispose()
    }
}