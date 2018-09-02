package com.alex.vedenyapin.imageloader.model.comment

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
@Entity
class Comment(val postId: Int,
              @field: PrimaryKey
              val id: Int,
              val name: String,
              val email: String,
              val body: String)