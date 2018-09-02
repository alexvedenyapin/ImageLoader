package com.alex.vedenyapin.imageloader.screens.comments.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.utils.IMAGE_ID

class CommentsActivity : AppCompatActivity() {

    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        imageId = intent.getIntExtra(IMAGE_ID, 0)
    }
}