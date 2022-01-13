package com.example.spacetrucking.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.main.data.PODServerResponseData
import com.example.spacetrucking.model.main.data.PictureOfTheDayData
import com.example.spacetrucking.model.main.datasource.RemoteDataSourceImpl
import com.example.spacetrucking.model.main.repository.RepositoryPictureEmpl
import retrofit2.Call
import retrofit2.Response

class MainViewModel(
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheDayData> =
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
                liveDataForViewToObserve.value = PictureOfTheDayData.Success(response.body()!!)
            } else {
                val message = response.message()

                if (message.isNullOrEmpty()) {
                    liveDataForViewToObserve.value =
                        PictureOfTheDayData.Error(Throwable("Unidentified error"))
                } else {
                    liveDataForViewToObserve.value =
                        PictureOfTheDayData.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
        }

    }

    fun getData(): LiveData<PictureOfTheDayData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(2)
        repositoryPicture.getWeatherDataFromServers(callBack)
    }

}