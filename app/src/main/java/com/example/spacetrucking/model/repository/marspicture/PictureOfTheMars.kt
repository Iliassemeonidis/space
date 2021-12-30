package com.example.spacetrucking.model.repository.marspicture

import com.example.spacetrucking.model.repository.marspicture.data.PODServerResponseMarsData

sealed class PictureOfTheMars{
    data class Success(val serverResponseMarsData: List<PODServerResponseMarsData>) : PictureOfTheMars()
    data class Error(val error: Throwable) : PictureOfTheMars()
    data class Loading(val progress: Int?) : PictureOfTheMars()
}
