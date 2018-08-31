package com.alex.vedenyapin.imageloader.ui.gallery

import com.alex.vedenyapin.imageloader.base.BaseViewModel
import com.alex.vedenyapin.imageloader.network.Api
import javax.inject.Inject

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
class GalleryViewModel: BaseViewModel() {

    @Inject
    lateinit var api: Api
}