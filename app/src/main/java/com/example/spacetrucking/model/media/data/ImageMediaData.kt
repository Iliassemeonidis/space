package com.example.spacetrucking.model.media.data

import com.google.gson.annotations.SerializedName

data class ImageMediaData(@SerializedName("items") val items: List<ServerResponseMedia>)
