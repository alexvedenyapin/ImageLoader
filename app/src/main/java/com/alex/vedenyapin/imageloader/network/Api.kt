package com.alex.vedenyapin.imageloader.network

import com.alex.vedenyapin.imageloader.model.comment.Comment
import com.alex.vedenyapin.imageloader.model.image.Image
import com.alex.vedenyapin.imageloader.utils.*
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
    fun getImages(@Query(QUERY_PAGE) page: Int = PAGE_NUMBER, @Query(QUERY_LIMIT) limit: Int = IMAGE_LIMIT_NUMBER): Observable<List<Image>>

    /**
     * Get the list of the comments from the API
     */
    @GET("/comments")
    fun getComments(@Query(QUERY_PAGE) page: Int = PAGE_NUMBER, @Query(QUERY_LIMIT) limit: Int = COMMENT_LIMIT_NUMBER): Observable<List<Comment>>
}