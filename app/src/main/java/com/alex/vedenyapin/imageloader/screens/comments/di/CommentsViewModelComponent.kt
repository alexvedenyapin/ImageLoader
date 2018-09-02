package com.alex.vedenyapin.imageloader.screens.comments.di

import com.alex.vedenyapin.imageloader.di.di.NetworkModule
import com.alex.vedenyapin.imageloader.screens.comments.ui.CommentsViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
@Singleton
@Component(modules = [(NetworkModule::class), (CommentsModule::class)])
interface CommentsViewModelComponent {

    fun inject(commentsViewModel: CommentsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): CommentsViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder

        fun commentsModule(commentsModule: CommentsModule): Builder
    }
}