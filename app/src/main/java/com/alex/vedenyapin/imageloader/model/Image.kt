package com.alex.vedenyapin.imageloader.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
@Entity
data class Image(
        val albumId: Int,
        @field: PrimaryKey
        val id: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String)