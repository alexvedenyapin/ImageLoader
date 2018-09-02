package com.alex.vedenyapin.imageloader.screens.comments.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.databinding.ActivityCommentsBinding
import com.alex.vedenyapin.imageloader.model.image.Image
import com.alex.vedenyapin.imageloader.screens.comments.di.CommentsListViewModelFactory
import com.alex.vedenyapin.imageloader.utils.IMAGE_ID
import com.squareup.picasso.Picasso

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentsBinding
    private lateinit var commentsListViewModel: CommentsListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBinding()
        setUpList()
        observeImage()
        observeError()
    }

    private fun setUpBinding() {
        val imageId = intent.getIntExtra(IMAGE_ID, 0)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments)
        commentsListViewModel = ViewModelProviders.of(this, CommentsListViewModelFactory(this, imageId)).get(CommentsListViewModel::class.java)
        binding.commentsListViewModel = commentsListViewModel
    }

    private fun setUpList() {
        binding.commentsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.commentsList.adapter = commentsListViewModel.commentsAdapter
    }

    private fun observeImage() {
        commentsListViewModel.bigImage.observe(this, Observer { image ->
            if (image != null) showImage(image)
        })
    }

    private fun showImage(image: Image) {
        Picasso.get().load(image.url).into(binding.bigImage)
    }

    private fun observeError() {
        commentsListViewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, commentsListViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}