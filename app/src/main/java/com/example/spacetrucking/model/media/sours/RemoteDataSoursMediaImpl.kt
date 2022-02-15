package com.example.spacetrucking.model.media.sours

import com.example.spacetrucking.MaterialApplication.Companion.getRetrofitImagesImpl
import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteDataSoursMediaImpl : RemoteDataSoursMedia {

    override fun getDataFromNasaMedia(): Single<PODServerResponseMediaData> =
        getRetrofitImagesImpl().getMediaNasa().subscribeOn(Schedulers.io())

}