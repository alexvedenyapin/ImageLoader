package com.alex.vedenyapin.imageloader.screens.gallery.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.alex.vedenyapin.imageloader.App
import com.alex.vedenyapin.imageloader.screens.gallery.ui.GalleryViewModel

/**
 * Created by Alex Vedenyapin on 01.09.2018
 */
class GalleryViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            val db = (activity.application as App).getDB()
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(db.imageDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}