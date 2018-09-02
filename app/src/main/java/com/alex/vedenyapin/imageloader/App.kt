package com.alex.vedenyapin.imageloader

import android.app.Application
import android.arch.persistence.room.Room
import com.alex.vedenyapin.imageloader.model.db.AppDatabase
import com.alex.vedenyapin.imageloader.utils.DB_NAME

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
class App: Application() {

    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        initDB()
    }

    private fun initDB() {
        db = Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME).build()
    }

    fun getDB() = db
}