package com.example.spacetrucking.model.main.datasource

import com.example.spacetrucking.model.main.data.PODServerResponseData
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getData(): Single<PODServerResponseData>
}