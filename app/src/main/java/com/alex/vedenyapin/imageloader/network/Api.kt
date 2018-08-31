package com.alex.vedenyapin.imageloader.network

import com.alex.vedenyapin.imageloader.model.Image
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Alex Vedenyapin on 31.08.2018
 */

/**
 * The interface which provides methods to get result of webservices
 */
interface Api {

    /**
     * Get the list of the images from the API
     */
    @GET("/photos")
    fun getImages(): Observable<List<Image>>
}