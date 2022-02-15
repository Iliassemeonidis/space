package com.example.spacetrucking.model.mars.soursinterface

import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

interface RemoteDataSoursMars {
    fun getDataFromMars():Single<PODServerResponseMarsData>
}