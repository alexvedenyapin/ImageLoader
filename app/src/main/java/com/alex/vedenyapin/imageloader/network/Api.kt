package com.alex.vedenyapin.imageloader.network

import com.alex.vedenyapin.imageloader.model.image.Image
import com.alex.vedenyapin.imageloader.utils.LIMIT
import com.alex.vedenyapin.imageloader.utils.LIMIT_NUMBER
import com.alex.vedenyapin.imageloader.utils.PAGE
import com.alex.vedenyapin.imageloader.utils.PAGE_NUMBER
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

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
    fun getImages(@Query(PAGE) page: Int = PAGE_NUMBER, @Query(LIMIT) limit: Int = LIMIT_NUMBER): Observable<List<Image>>
}