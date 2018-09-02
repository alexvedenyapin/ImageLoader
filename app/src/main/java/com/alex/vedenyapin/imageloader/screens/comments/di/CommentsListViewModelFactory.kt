package com.alex.vedenyapin.imageloader.screens.comments.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.alex.vedenyapin.imageloader.App
import com.alex.vedenyapin.imageloader.screens.comments.ui.CommentsListViewModel

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class CommentsListViewModelFactory(private val activity: AppCompatActivity, private val imageId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsListViewModel::class.java)) {
            val db = (activity.application as App).getDB()
            @Suppress("UNCHECKED_CAST")
            return CommentsListViewModel(imageId, db.imageDao(), db.commentDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}