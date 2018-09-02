package com.alex.vedenyapin.imageloader.screens.comments.di

import com.alex.vedenyapin.imageloader.di.di.NetworkModule
import com.alex.vedenyapin.imageloader.screens.comments.ui.CommentsListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
@Singleton
@Component(modules = [(NetworkModule::class), (CommentsModule::class)])
interface CommentsListViewModelComponent {

    fun inject(commentsListViewModel: CommentsListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): CommentsListViewModelComponent

        fun networkModule(networkModule: NetworkModule): Builder

        fun commentsModule(commentsModule: CommentsModule): Builder
    }
}