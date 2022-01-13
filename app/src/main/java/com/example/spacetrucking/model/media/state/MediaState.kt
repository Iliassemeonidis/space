package com.example.spacetrucking.model.media.state

import com.example.spacetrucking.model.media.data.PODServerResponseMediaData

sealed class MediaState {
    data class Success(val podServerResponseMediaData: PODServerResponseMediaData) : MediaState()
    data class Error(val error : Throwable) : MediaState()
    data class Loading(val loading : Int?) : MediaState()
}
