package com.example.spacetrucking.model.media.sours

import com.example.spacetrucking.model.MaterialApplication.Companion.getRetrofitImagesImpl
import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import retrofit2.Callback

class RemoteDataSoursMediaImpl : RemoteDataSoursMedia {

    override fun getDataFromNasaMedia(callback: Callback<PODServerResponseMediaData>) {
        getRetrofitImagesImpl().getMediaNasa().enqueue(callback)
    }
}