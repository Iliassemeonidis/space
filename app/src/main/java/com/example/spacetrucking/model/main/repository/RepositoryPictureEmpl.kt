package com.example.spacetrucking.model.main.repository

import com.example.spacetrucking.model.main.data.PODServerResponseData
import com.example.spacetrucking.model.main.datasource.RemoteDataSourceImpl
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

class RepositoryPictureEmpl(private val remoteDataSourceImpl: RemoteDataSourceImpl) :
    RepositoryPicture {

    override fun getWeatherDataFromServers() = remoteDataSourceImpl.getData()

}