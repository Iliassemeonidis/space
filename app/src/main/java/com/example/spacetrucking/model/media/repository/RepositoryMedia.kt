package com.example.spacetrucking.model.media.repository

import com.example.spacetrucking.model.media.data.PODServerResponseMediaData

interface RepositoryMedia {
    fun getMediaFromServer(callback: retrofit2.Callback<PODServerResponseMediaData>)
}