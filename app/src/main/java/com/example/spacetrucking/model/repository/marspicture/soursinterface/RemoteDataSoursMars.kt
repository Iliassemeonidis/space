package com.example.spacetrucking.model.repository.marspicture.soursinterface

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.model.MaterialApplication.Companion.getRetrofitMarsImpl
import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import retrofit2.Callback

class RemoteDataSoursMars {
    fun getData(callback: Callback<List<PODServerResponseMarsData>>) {
        getRetrofitMarsImpl().getMarsPicture(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
}