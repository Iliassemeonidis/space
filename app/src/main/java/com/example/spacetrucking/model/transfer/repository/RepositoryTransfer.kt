package com.example.spacetrucking.model.transfer.repository

import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData

interface RepositoryTransfer {
    fun getMediaFromServer(callback: retrofit2.Callback<PODServerResponseTechTransferData>)
}