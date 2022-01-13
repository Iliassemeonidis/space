package com.example.spacetrucking.model.transfer.state

import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData

sealed class TransferState {

    data class Success(val serverResponseMarsData:PODServerResponseTechTransferData ) : TransferState()
    data class Error(val error: Throwable) : TransferState()
    data class Loading(val progress: Int?) : TransferState()

}