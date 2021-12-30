package com.example.spacetrucking.ui.fragmentmars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.repository.marspicture.StatePictureOfTheMars
import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData
import com.example.spacetrucking.model.repository.marspicture.repository.RepositoryMarsEmpl
import com.example.spacetrucking.model.repository.marspicture.soursinterface.RemoteDataSoursMars
import retrofit2.Call
import retrofit2.Response

class MarsViewModel(
    private val _mutableLiveData: MutableLiveData<StatePictureOfTheMars> =
        MutableLiveData(),
    private val repositoryPicture: RepositoryMarsEmpl = RepositoryMarsEmpl(
        RemoteDataSoursMars()
    )
) : ViewModel() {


    private val stateToObserve: LiveData<StatePictureOfTheMars>
        get() = _mutableLiveData


    private val callBack = object : retrofit2.Callback<PODServerResponseMarsData> {
        override fun onResponse(
            call: Call<PODServerResponseMarsData>,
            response: Response<PODServerResponseMarsData>
        ) {
            if (response.isSuccessful && response.body() != null) {
                _mutableLiveData.value = StatePictureOfTheMars.Success(response.body()!!)
            } else {
                val message = response.message()

                if (message.isNullOrEmpty()) {
                    _mutableLiveData.value =
                        StatePictureOfTheMars.Error(Throwable("Unidentified error"))
                } else {
                    _mutableLiveData.value =
                        StatePictureOfTheMars.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PODServerResponseMarsData>, t: Throwable) {
            _mutableLiveData.value = StatePictureOfTheMars.Error(t)
        }
    }

    fun subscribeToStateChange(): LiveData<StatePictureOfTheMars> {
        return stateToObserve
    }

    fun getData() {
        sendServerRequest()
    }

    private fun sendServerRequest() {
        _mutableLiveData.value = StatePictureOfTheMars.Loading(2)
        repositoryPicture.getDataMarsFromServers(callBack)
    }
}