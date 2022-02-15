package com.example.spacetrucking.model.transfer.repository

import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import io.reactivex.rxjava3.core.Single

interface RepositoryTransfer {
    fun getMediaFromServer() : Single<PODServerResponseTechTransferData>
}