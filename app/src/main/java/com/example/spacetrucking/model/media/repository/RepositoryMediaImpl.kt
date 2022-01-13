package com.example.spacetrucking.model.media.repository

import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import com.example.spacetrucking.model.media.sours.RemoteDataSoursMediaImpl
import retrofit2.Callback

class RepositoryMediaImpl(private val remoteDataSoursMediaImpl: RemoteDataSoursMediaImpl) : RepositoryMedia {

    override fun getMediaFromServer(callback: Callback<PODServerResponseMediaData>) {
        remoteDataSoursMediaImpl.getDataFromNasaMedia(callback)
    }
}