package com.example.spacetrucking.model.main.repository

import com.example.spacetrucking.model.main.data.PODServerResponseData
import io.reactivex.rxjava3.core.Single

interface RepositoryPicture {
    fun getWeatherDataFromServers(): Single<PODServerResponseData>
}