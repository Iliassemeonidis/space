package com.example.spacetrucking.model.main.datasource

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.model.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.main.data.PODServerResponseData
import retrofit2.Callback

class RemoteDataSourceImpl : RemoteDataSource {

    override fun getData(callback: Callback<PODServerResponseData>) {
        getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}