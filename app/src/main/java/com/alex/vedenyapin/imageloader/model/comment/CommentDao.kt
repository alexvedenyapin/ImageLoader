package com.alex.vedenyapin.imageloader.model.comment

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
@Dao
interface CommentDao {

    @get:Query("SELECT * FROM comment")
    val all: List<Comment>

    @Insert
    fun insertAll(vararg comments: Comment)
}