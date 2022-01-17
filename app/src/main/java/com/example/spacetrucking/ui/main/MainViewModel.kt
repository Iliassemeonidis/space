package com.example.spacetrucking.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.main.data.PODServerResponseData
import com.example.spacetrucking.model.main.data.PictureOfTheDayState
import com.example.spacetrucking.model.main.datasource.RemoteDataSourceImpl
import com.example.spacetrucking.model.main.repository.RepositoryPictureEmpl
import retrofit2.Call
import retrofit2.Response

class MainViewModel(
    private val liveStateForViewToObserve: MutableLiveData<PictureOfTheDayState> =
        MutableLiveData(),
    private val repositoryPicture: RepositoryPictureEmpl = RepositoryPictureEmpl(
        RemoteDataSourceImpl()
    )
) : ViewModel() {

    private val callBack = object : retrofit2.Callback<PODServerResponseData> {
        override fun onResponse(
            call: Call<PODServerResponseData>,
            response: Response<PODServerResponseData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveStateForViewToObserve.value = PictureOfTheDayState.Success(response.body()!!)
            } else {
                val message = response.message()

                if (message.isNullOrEmpty()) {
                    liveStateForViewToObserve.value =
                        PictureOfTheDayState.Error(Throwable("Unidentified error"))
                } else {
                    liveStateForViewToObserve.value =
                        PictureOfTheDayState.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            liveStateForViewToObserve.value = PictureOfTheDayState.Error(t)
        }

    }

    fun getData(): LiveData<PictureOfTheDayState> {
        sendServerRequest()
        return liveStateForViewToObserve
    }

    private fun sendServerRequest() {
        liveStateForViewToObserve.value = PictureOfTheDayState.Loading(2)
        repositoryPicture.getWeatherDataFromServers(callBack)
    }

}