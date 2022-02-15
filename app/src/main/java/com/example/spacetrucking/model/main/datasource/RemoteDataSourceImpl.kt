package com.example.spacetrucking.model.main.datasource

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.main.data.PODServerResponseData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Callback

class RemoteDataSourceImpl : RemoteDataSource {
    override fun getData(): Single<PODServerResponseData> = getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).subscribeOn(Schedulers.io())
}