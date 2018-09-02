package com.alex.vedenyapin.imageloader.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.alex.vedenyapin.imageloader.model.comment.Comment
import com.alex.vedenyapin.imageloader.model.comment.CommentDao
import com.alex.vedenyapin.imageloader.model.image.Image
import com.alex.vedenyapin.imageloader.model.image.ImageDao

/**
 * Created by Alex Vedenyapin on 01.09.2018
 */
@Database(entities = arrayOf(Image::class, Comment::class), version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun imageDao(): ImageDao

    abstract fun commentDao(): CommentDao
}