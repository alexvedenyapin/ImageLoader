package com.alex.vedenyapin.imageloader.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Alex Vedenyapin on 01.09.2018
 */
@Dao
interface ImageDao {
    @get:Query("SELECT * FROM image")
    val all: List<Image>

    @Insert
    fun insertAll(vararg images: Image)
}