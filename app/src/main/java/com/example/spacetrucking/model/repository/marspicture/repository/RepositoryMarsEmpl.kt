package com.example.spacetrucking.model.repository.marspicture.repository

import com.example.spacetrucking.model.repository.marspicture.soursinterface.RemoteDataSoursMars
import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import retrofit2.Callback

class RepositoryMarsEmpl(private val remoteDataSource: RemoteDataSoursMars): RepositoryMars {

    override fun getDataMarsFromServers(callback: Callback<List<PODServerResponseMarsData>>) {
        remoteDataSource.getData(callback)
    }

}