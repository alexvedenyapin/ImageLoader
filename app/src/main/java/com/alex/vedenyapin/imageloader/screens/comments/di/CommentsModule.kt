package com.alex.vedenyapin.imageloader.screens.comments.di

import com.alex.vedenyapin.imageloader.network.Api
import com.alex.vedenyapin.imageloader.screens.comments.domain.CommentsInteractor
import dagger.Module
import dagger.Provides

/**
 * Created by Alex Vedenyapin on 02.09.2018
 */
@Module
class CommentsModule {

    @Provides
    fun provideCommentsInteractor(api: Api): CommentsInteractor {
        return CommentsInteractor(api)
    }
}