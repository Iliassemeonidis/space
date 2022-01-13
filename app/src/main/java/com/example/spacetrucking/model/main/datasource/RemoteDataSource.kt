package com.example.spacetrucking.model.main.datasource

import com.example.spacetrucking.model.main.data.PODServerResponseData
import retrofit2.Callback

interface RemoteDataSource {

    fun getData(callback: Callback<PODServerResponseData>)
}