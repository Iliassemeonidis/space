package com.example.spacetrucking.model.main.repository

import com.example.spacetrucking.model.main.data.PODServerResponseData

interface RepositoryPicture {
    fun getWeatherDataFromServers(
        callback: retrofit2.Callback<PODServerResponseData>
    )
}