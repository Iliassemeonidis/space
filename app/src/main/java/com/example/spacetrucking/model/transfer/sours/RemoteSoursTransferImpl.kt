package com.example.spacetrucking.model.transfer.sours

import com.example.spacetrucking.BuildConfig
import com.example.spacetrucking.MaterialApplication.Companion.getRetrofitImpl
import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import retrofit2.Callback

class RemoteSoursTransferImpl : RemoteSoursTransfer {

    override fun getDataSoursFromNasaTransfer(callback: Callback<PODServerResponseTechTransferData>) {
        getRetrofitImpl().getTechTransfer(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
//    override  fun <T> getDataSoursFromNasaTransfer(callback: Callback<T>) {
//        getRetrofitImpl().getTechTransfer(BuildConfig.NASA_API_KEY).enqueue(callback)
//    }
}