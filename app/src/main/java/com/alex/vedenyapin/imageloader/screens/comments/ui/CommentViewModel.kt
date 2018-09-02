package com.alex.vedenyapin.imageloader.screens.comments.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alex.vedenyapin.imageloader.model.comment.Comment

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class CommentViewModel: ViewModel() {
    private val commentTitle = MutableLiveData<String>()
    private val commentBody = MutableLiveData<String>()

    fun bind(comment: Comment){
        commentTitle.value = comment.name
        commentBody.value = comment.body
    }

    fun getCommentTitle(): MutableLiveData<String> {
        return commentTitle
    }

    fun getCommentBody(): MutableLiveData<String> {
        return commentBody
    }
}