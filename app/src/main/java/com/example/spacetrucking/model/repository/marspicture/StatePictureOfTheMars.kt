package com.example.spacetrucking.model.repository.marspicture

import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData

sealed class StatePictureOfTheMars {
    data class Success(val serverResponseMarsData: PODServerResponseMarsData) : StatePictureOfTheMars()
    data class Error(val error: Throwable) : StatePictureOfTheMars()
    data class Loading(val progress: Int?) : StatePictureOfTheMars()
}
