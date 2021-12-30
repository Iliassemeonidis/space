package com.example.spacetrucking.model.repository.marspicture.repository

import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData

interface RepositoryMars {
    fun getDataMarsFromServers(
        callback: retrofit2.Callback<PODServerResponseMarsData>
    )
}