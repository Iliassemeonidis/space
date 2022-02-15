package com.example.spacetrucking.ui.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.transfer.repository.RepositoryTransferImpl
import com.example.spacetrucking.model.transfer.sours.RemoteSoursTransferImpl
import com.example.spacetrucking.model.transfer.state.TransferState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TechTransferViewModel(
    private val _liveDataForViewToObserve: MutableLiveData<TransferState> = MutableLiveData(),
    private val repositoryTransferImpl: RepositoryTransferImpl = RepositoryTransferImpl(
        RemoteSoursTransferImpl()
    )
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val stateToObserve: LiveData<TransferState>
        get() = _liveDataForViewToObserve

    fun subscribeToStateChange(): LiveData<TransferState> {
        return stateToObserve
    }

    private fun sendServerRequest() {
        _liveDataForViewToObserve.value = TransferState.Loading(2)
        compositeDisposable.add(
            repositoryTransferImpl.getMediaFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _liveDataForViewToObserve.value = TransferState.Success(it)
                }, {
                    _liveDataForViewToObserve.value = TransferState.Error(Throwable(it))
                })
        )
    }

    fun getData() {
        sendServerRequest()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}