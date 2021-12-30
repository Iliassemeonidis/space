package com.example.spacetrucking.model.repository.marspicture.soursinterface

import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPictureAPI {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&")
    fun getMarsPicture(@Query("api_key")apiKey: String): Call<PODServerResponseMarsData>
}