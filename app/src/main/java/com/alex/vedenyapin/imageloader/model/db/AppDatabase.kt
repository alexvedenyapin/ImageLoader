package com.alex.vedenyapin.imageloader.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.alex.vedenyapin.imageloader.model.Image
import com.alex.vedenyapin.imageloader.model.ImageDao

/**
 * Created by Alex Vedenyapin on 01.09.2018
 */
@Database(entities = arrayOf(Image::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun imageDao(): ImageDao
}