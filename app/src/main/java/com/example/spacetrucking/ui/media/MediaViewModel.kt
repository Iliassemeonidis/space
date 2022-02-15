package com.example.spacetrucking.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.media.repository.RepositoryMediaImpl
import com.example.spacetrucking.model.media.sours.RemoteDataSoursMediaImpl
import com.example.spacetrucking.model.media.state.MediaState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MediaViewModel(
    private val _liveDataForViewToObserve: MutableLiveData<MediaState> = MutableLiveData(),
    private val repositoryMediaImpl: RepositoryMediaImpl = RepositoryMediaImpl(
        RemoteDataSoursMediaImpl()
    )
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val stateToObserve: LiveData<MediaState>
        get() = _liveDataForViewToObserve

    fun subscribeToStateChange(): LiveData<MediaState> {
        return stateToObserve
    }

    fun getData() {
        sendServerRequest()
    }

    private fun sendServerRequest() {
        _liveDataForViewToObserve.value = MediaState.Loading(2)

        compositeDisposable.add(
            repositoryMediaImpl.getMediaFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _liveDataForViewToObserve.value = MediaState.Success(it)
                }, { t ->
                    _liveDataForViewToObserve.value = MediaState.Error(t)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}