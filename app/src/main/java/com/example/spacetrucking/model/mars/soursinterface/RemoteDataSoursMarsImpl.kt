package com.example.spacetrucking.model.mars.soursinterface

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Callback
import java.util.concurrent.ScheduledFuture

class RemoteDataSoursMarsImpl : RemoteDataSoursMars {
    override fun getDataFromMars(): Single<PODServerResponseMarsData> = getRetrofitImpl().getMarsPicture(BuildConfig.NASA_API_KEY).subscribeOn(
        Schedulers.io())
}