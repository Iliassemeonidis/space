package com.example.spacetrucking.model.mars.soursinterface

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import retrofit2.Callback

class RemoteDataSoursMarsImpl : RemoteDataSoursMars {

    override fun getDataFromMars(callback: Callback<PODServerResponseMarsData>) {
        getRetrofitImpl().getMarsPicture(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}