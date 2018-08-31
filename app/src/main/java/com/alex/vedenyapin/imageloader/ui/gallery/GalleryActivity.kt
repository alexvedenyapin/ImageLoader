package com.alex.vedenyapin.imageloader.ui.gallery

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var viewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        binding.postList.layoutManager = GridLayoutManager(this, GridLayoutManager.VERTICAL, GridLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        binding.viewModel = viewModel
    }
}
