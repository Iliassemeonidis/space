package com.example.spacetrucking.model.repository.picture

import com.example.spacetrucking.model.network.PODServerResponseData

interface RepositoryPicture {
    fun getWeatherDataFromServers(
        callback: retrofit2.Callback<PODServerResponseData>
    )
}