package com.example.spacetrucking.model.media.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ServerResponseMedia(
    @SerializedName("data") val data: List<DataMedia>?,
    @SerializedName("links") val links: List<ImageData>?
) : Serializable
