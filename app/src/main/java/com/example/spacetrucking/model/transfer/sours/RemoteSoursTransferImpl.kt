package com.example.spacetrucking.model.transfer.sours

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RemoteSoursTransferImpl : RemoteSoursTransfer {
    override fun getDataSoursFromNasaTransfer(): Single<PODServerResponseTechTransferData> =
        getRetrofitImpl().getTechTransfer(BuildConfig.NASA_API_KEY).subscribeOn(Schedulers.io())
}