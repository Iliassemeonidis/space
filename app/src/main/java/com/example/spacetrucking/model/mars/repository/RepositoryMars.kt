package com.example.spacetrucking.model.mars.repository

import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import io.reactivex.rxjava3.core.Single

interface RepositoryMars {
    fun getDataMarsFromServers(): Single<PODServerResponseMarsData>
}