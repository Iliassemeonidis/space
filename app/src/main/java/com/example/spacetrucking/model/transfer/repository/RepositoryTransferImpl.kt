package com.example.spacetrucking.model.transfer.repository

import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import com.example.spacetrucking.model.transfer.sours.RemoteSoursTransferImpl
import retrofit2.Callback

class RepositoryTransferImpl(private val remoteSoursTransfer: RemoteSoursTransferImpl):
    RepositoryTransfer
//    RepositoryInter
{

    override fun getMediaFromServer(callback: Callback<PODServerResponseTechTransferData>) {
        remoteSoursTransfer.getDataSoursFromNasaTransfer(callback)
    }

//    override fun <T> getMediaFromServer(callback: Callback<T>) {
//        remoteSoursTransfer.getDataSoursFromNasaTransfer(callback)
//    }

}