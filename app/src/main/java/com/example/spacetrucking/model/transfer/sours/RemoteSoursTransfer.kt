package com.example.spacetrucking.model.transfer.sours

import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

interface RemoteSoursTransfer {
        fun getDataSoursFromNasaTransfer() :Single<PODServerResponseTechTransferData>
}