package com.example.spacetrucking.model.mars.data

import com.google.gson.annotations.SerializedName

data class PhotosData(
    @SerializedName("id") val id: String?,
    @SerializedName("sol") val sol: String?,
    @SerializedName("camera") val camera: CameraData?,
    @SerializedName("img_src") val imgSrc: String?,
    @SerializedName("earth_date") val earthDate: String?,
)
