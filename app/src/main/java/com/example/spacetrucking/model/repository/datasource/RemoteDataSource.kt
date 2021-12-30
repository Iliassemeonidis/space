package com.example.spacetrucking.model.repository.datasource

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.model.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.network.PODServerResponseData
import retrofit2.Callback

class RemoteDataSource {
    fun getData(callback: Callback<PODServerResponseData>) {
        getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}