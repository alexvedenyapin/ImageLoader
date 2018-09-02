package com.alex.vedenyapin.imageloader.screens.comments.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.databinding.ActivityCommentsBinding
import com.alex.vedenyapin.imageloader.model.image.Image
import com.alex.vedenyapin.imageloader.screens.comments.di.CommentsViewModelFactory
import com.alex.vedenyapin.imageloader.utils.IMAGE_ID
import com.squareup.picasso.Picasso

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private lateinit var viewModel: CommentsViewModel

    private var errorSnackbar: Snackbar? = null
    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageId = intent.getIntExtra(IMAGE_ID, 0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments)
        viewModel = ViewModelProviders.of(this, CommentsViewModelFactory(this, imageId)).get(CommentsViewModel::class.java)

        setUpList()
        observeImage()
        observeError()

        binding.viewModel = viewModel
    }

    private fun setUpList() {
//        binding.imageList.layoutManager = GridLayoutManager(this, NUMBER_OF_GALLERY_COLUMNS)
//        val imageGridAdapter = viewModel.imageGridAdapter
//        imageGridAdapter.setImageListener(this)
//        binding.imageList.adapter = imageGridAdapter
    }

    private fun observeImage() {
        viewModel.bigImage.observe(this, Observer { image ->
            if (image != null) showImage(image)
        })
    }

    private fun showImage(image: Image) {
        Picasso.get().load(image.url).into(binding.bigImage)
    }

    private fun observeError() {
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}