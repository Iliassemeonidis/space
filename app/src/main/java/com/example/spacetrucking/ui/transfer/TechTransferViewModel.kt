package com.example.spacetrucking.ui.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.transfer.data.PODServerResponseTechTransferData
import com.example.spacetrucking.model.transfer.repository.RepositoryTransferImpl
import com.example.spacetrucking.model.transfer.sours.RemoteSoursTransferImpl
import com.example.spacetrucking.model.transfer.state.TransferState
import retrofit2.Call
import retrofit2.Response

class TechTransferViewModel(
    private val _liveDataForViewToObserve: MutableLiveData<TransferState> = MutableLiveData(),
    private val repositoryTransferImpl: RepositoryTransferImpl = RepositoryTransferImpl(
        RemoteSoursTransferImpl()
    )
) : ViewModel() {

    private val stateToObserve:LiveData<TransferState>
    get() = _liveDataForViewToObserve

    private val callBack = object : retrofit2.Callback<PODServerResponseTechTransferData> {

        override fun onResponse(
            call: Call<PODServerResponseTechTransferData>,
            response: Response<PODServerResponseTechTransferData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                _liveDataForViewToObserve.value = TransferState.Success(response.body()!!)
            } else {
                val message  = response.message()

                if (message.isNullOrBlank()) {
                    _liveDataForViewToObserve.value =
                        TransferState.Error(Throwable("Unidentified error"))
                } else {
                    _liveDataForViewToObserve.value = TransferState.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseTechTransferData>, t: Throwable) {
            _liveDataForViewToObserve.value = TransferState.Error(Throwable(t))
        }
    }

    fun subscribeToStateChange(): LiveData<TransferState> {
        return stateToObserve
    }

    private fun sendServerRequest() {
        _liveDataForViewToObserve.value = TransferState.Loading(2)
        repositoryTransferImpl.getMediaFromServer(callBack)
    }

    fun getData() {
        sendServerRequest()
    }
}