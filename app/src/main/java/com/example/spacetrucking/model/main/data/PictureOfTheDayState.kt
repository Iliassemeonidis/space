package com.example.spacetrucking.model.main.data

sealed class PictureOfTheDayState {
    data class Success(val serverResponseData: PODServerResponseData) : PictureOfTheDayState()
    data class Error(val error: Throwable) : PictureOfTheDayState()
    data class Loading(val progress: Int?) : PictureOfTheDayState()
}
