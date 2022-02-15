package com.example.spacetrucking.model.transfer.repository

import com.example.spacetrucking.model.transfer.sours.RemoteSoursTransferImpl

class RepositoryTransferImpl(private val remoteSoursTransfer: RemoteSoursTransferImpl) :
    RepositoryTransfer {
    override fun getMediaFromServer() = remoteSoursTransfer.getDataSoursFromNasaTransfer()
}