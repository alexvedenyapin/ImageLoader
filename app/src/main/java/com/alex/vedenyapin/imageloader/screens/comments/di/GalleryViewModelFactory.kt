package com.alex.vedenyapin.imageloader.screens.comments.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.alex.vedenyapin.imageloader.model.db.AppDatabase
import com.alex.vedenyapin.imageloader.screens.comments.ui.CommentsViewModel

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class CommentsViewModelFactory(private val activity: AppCompatActivity, private val imageId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "images").build()
            @Suppress("UNCHECKED_CAST")
            return CommentsViewModel(db.imageDao(), imageId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}