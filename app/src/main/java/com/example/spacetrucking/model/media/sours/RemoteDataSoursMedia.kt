package com.example.spacetrucking.model.media.sours

import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import io.reactivex.rxjava3.core.Single

interface RemoteDataSoursMedia {
    fun getDataFromNasaMedia(): Single<PODServerResponseMediaData>
}