package com.example.spacetrucking.ui.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import com.example.spacetrucking.model.mars.repository.RepositoryMarsEmpl
import com.example.spacetrucking.model.mars.soursinterface.RemoteDataSoursMarsImpl
import com.example.spacetrucking.model.mars.state.PictureOfTheMars
import retrofit2.Call
import retrofit2.Response

class MarsViewModel(
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheMars> =
        MutableLiveData(),
    private val repositoryPicture: RepositoryMarsEmpl = RepositoryMarsEmpl(
        RemoteDataSoursMarsImpl()
    )
) : ViewModel() {
    private val callBack = object : retrofit2.Callback<PODServerResponseMarsData> {
        override fun onResponse(
            call: Call<PODServerResponseMarsData>,
            response: Response<PODServerResponseMarsData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveDataForViewToObserve.value = PictureOfTheMars.Success(response.body()!!)
            } else {
                val message = response.message()

                if (message.isNullOrEmpty()) {
                    liveDataForViewToObserve.value =
                        PictureOfTheMars.Error(Throwable("Unidentified error"))
                } else {
                    liveDataForViewToObserve.value =
                        PictureOfTheMars.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseMarsData>, t: Throwable) {
            liveDataForViewToObserve.value = PictureOfTheMars.Error(t)
        }
    }

    fun getData(): LiveData<PictureOfTheMars> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = PictureOfTheMars.Loading(2)
        repositoryPicture.getDataMarsFromServers(callBack)
    }
}