package com.example.spacetrucking.model.repository.picture

import com.example.spacetrucking.model.network.PODServerResponseData
import com.example.spacetrucking.model.repository.datasource.RemoteDataSource
import retrofit2.Callback

class RepositoryPictureEmpl(private val remoteDataSource: RemoteDataSource): RepositoryPicture {

    override fun getWeatherDataFromServers(callback: Callback<PODServerResponseData>) {
        remoteDataSource.getData(callback)
    }
}