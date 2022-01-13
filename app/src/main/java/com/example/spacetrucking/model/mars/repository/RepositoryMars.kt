package com.example.spacetrucking.model.mars.repository

import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData

interface RepositoryMars {
    fun getDataMarsFromServers(callback: retrofit2.Callback<PODServerResponseMarsData>)
}