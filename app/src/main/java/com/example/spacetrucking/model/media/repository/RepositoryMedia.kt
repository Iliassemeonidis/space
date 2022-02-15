package com.example.spacetrucking.model.media.repository

import com.example.spacetrucking.model.media.data.PODServerResponseMediaData
import io.reactivex.rxjava3.core.Single

interface RepositoryMedia {
    fun getMediaFromServer() : Single<PODServerResponseMediaData>
}