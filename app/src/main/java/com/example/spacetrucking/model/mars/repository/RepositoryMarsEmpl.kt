package com.example.spacetrucking.model.mars.repository

import com.example.spacetrucking.model.mars.soursinterface.RemoteDataSoursMarsImpl
import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

class RepositoryMarsEmpl(private val remoteDataSource: RemoteDataSoursMarsImpl): RepositoryMars {
    override fun getDataMarsFromServers()=remoteDataSource.getDataFromMars()
}