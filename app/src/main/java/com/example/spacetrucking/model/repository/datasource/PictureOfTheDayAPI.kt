package com.example.spacetrucking.model.repository.datasource

import com.example.spacetrucking.model.network.PODServerResponseData
import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
     fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<PODServerResponseData>
}

interface MarsPictureAPI {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&")
    fun getMarsPicture(@Query("api_key")apiKey: String): Call<List<PODServerResponseMarsData>>
}
