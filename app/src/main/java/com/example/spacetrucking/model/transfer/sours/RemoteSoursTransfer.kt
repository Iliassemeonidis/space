package com.example.spacetrucking.model.transfer.sours

import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import retrofit2.Callback

interface RemoteSoursTransfer {
        fun getDataSoursFromNasaTransfer(callback: Callback<PODServerResponseTechTransferData>)
//    fun <T> getDataSoursFromNasaTransfer(callback: Callback<T>)
}