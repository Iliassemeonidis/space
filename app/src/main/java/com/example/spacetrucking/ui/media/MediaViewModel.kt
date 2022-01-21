package com.example.spacetrucking.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import com.example.spacetrucking.model.media.repository.RepositoryMediaImpl
import com.example.spacetrucking.model.media.sours.RemoteDataSoursMediaImpl
import com.example.spacetrucking.model.media.state.MediaState
import retrofit2.Call
import retrofit2.Response

class MediaViewModel(
    private val _liveDataForViewToObserve: MutableLiveData<MediaState> = MutableLiveData(),
    private val repositoryMediaImpl: RepositoryMediaImpl = RepositoryMediaImpl(
        RemoteDataSoursMediaImpl()
    )
) : ViewModel() {

    private val stateToObserve: LiveData<MediaState>
        get() = _liveDataForViewToObserve

    private val callBack = object : retrofit2.Callback<PODServerResponseMediaData> {
        override fun onResponse(
            call: Call<PODServerResponseMediaData>,
            response: Response<PODServerResponseMediaData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                _liveDataForViewToObserve.value = MediaState.Success(response.body()!!)
            } else {
                val message = response.message()

                if (message.isNullOrEmpty()) {
                    _liveDataForViewToObserve.value =
                        MediaState.Error(Throwable("Unidentified error"))
                } else {
                    _liveDataForViewToObserve.value =
                        MediaState.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseMediaData>, t: Throwable) {
            _liveDataForViewToObserve.value = MediaState.Error(t)
        }
    }

    fun subscribeToStateChange(): LiveData<MediaState> {
        return stateToObserve
    }

    fun getData() {
        sendServerRequest()
    }

    private fun sendServerRequest() {
        _liveDataForViewToObserve.value = MediaState.Loading(2)
        repositoryMediaImpl.getMediaFromServer(callBack)
    }
}