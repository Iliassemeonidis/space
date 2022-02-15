package com.example.spacetrucking.model

import com.example.spacetrucking.model.main.data.PODServerResponseData
import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {

    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Single<PODServerResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&")
    fun getMarsPicture(@Query("api_key") apiKey: String): Single<PODServerResponseMarsData>

    @GET("techtransfer/patent/?engine")
    fun getTechTransfer(@Query("api_key") apiKey: String): Single<PODServerResponseTechTransferData>


    @GET("search?q=apollo%2011&description=moon%20landing&media_type=image")
    fun getMediaNasa(): Single<PODServerResponseMediaData>

}