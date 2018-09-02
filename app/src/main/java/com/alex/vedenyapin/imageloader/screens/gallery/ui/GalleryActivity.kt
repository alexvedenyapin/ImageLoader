package com.alex.vedenyapin.imageloader.screens.gallery.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.databinding.ActivityGalleryBinding
import com.alex.vedenyapin.imageloader.screens.gallery.di.GalleryViewModelFactory
import com.alex.vedenyapin.imageloader.utils.NUMBER_OF_GALLERY_COLUMNS
import com.alex.vedenyapin.imageloader.utils.IMAGE_ID
import android.content.Intent
import com.alex.vedenyapin.imageloader.screens.comments.ui.CommentsActivity


class GalleryActivity : AppCompatActivity(), ImageGridAdapter.ImageListener {

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var galleryViewModel: GalleryViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        galleryViewModel = ViewModelProviders.of(this, GalleryViewModelFactory(this)).get(GalleryViewModel::class.java)

        setUpList()
        observeError()

        binding.galleryViewModel = galleryViewModel
    }

    private fun setUpList() {
        binding.imageList.layoutManager = GridLayoutManager(this, NUMBER_OF_GALLERY_COLUMNS)
        val imageGridAdapter = galleryViewModel.imageGridAdapter
        imageGridAdapter.setImageListener(this)
        binding.imageList.adapter = imageGridAdapter
    }

    private fun observeError() {
        galleryViewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, galleryViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onImageClicked(imageId: Int) {
        val intent = Intent(baseContext, CommentsActivity::class.java)
        intent.putExtra(IMAGE_ID, imageId)
        startActivity(intent)
    }
}
