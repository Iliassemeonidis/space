package com.example.spacetrucking.model.mars.state

import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData

sealed class PictureOfTheMars{
    data class Success(val serverResponseMarsData: PODServerResponseMarsData) : PictureOfTheMars()
    data class Error(val error: Throwable) : PictureOfTheMars()
    data class Loading(val progress: Int?) : PictureOfTheMars()
}
