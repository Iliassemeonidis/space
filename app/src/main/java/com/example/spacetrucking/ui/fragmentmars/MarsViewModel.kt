package com.example.spacetrucking.ui.fragmentmars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import com.example.spacetrucking.model.repository.marspicture.PictureOfTheMars
import com.example.spacetrucking.model.repository.marspicture.soursinterface.RemoteDataSoursMars
import com.example.spacetrucking.model.repository.marspicture.repository.RepositoryMarsEmpl
import retrofit2.Call
import retrofit2.Response

class MarsViewModel(private val liveDataForViewToObserve: MutableLiveData<PictureOfTheMars> =
                           MutableLiveData(),
                    private val repositoryPicture: RepositoryMarsEmpl = RepositoryMarsEmpl(
                           RemoteDataSoursMars()
                       )
) : ViewModel() {
    private val callBack = object : retrofit2.Callback<List<PODServerResponseMarsData>> {
        override fun onResponse(
            call: Call<List<PODServerResponseMarsData>>,
            response: Response<List<PODServerResponseMarsData>>
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

        override fun onFailure(call: Call<List<PODServerResponseMarsData>>, t: Throwable) {
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