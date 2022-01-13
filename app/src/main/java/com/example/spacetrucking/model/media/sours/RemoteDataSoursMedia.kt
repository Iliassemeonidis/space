package com.example.spacetrucking.model.media.sours

import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import retrofit2.Callback


interface  RemoteDataSoursMedia {

    fun getDataFromNasaMedia(callback: Callback<PODServerResponseMediaData>)
}