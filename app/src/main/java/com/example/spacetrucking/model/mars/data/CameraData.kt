package com.example.spacetrucking.model.mars.data

import com.google.gson.annotations.SerializedName

data class CameraData(
    @SerializedName("name") val name: String?,
    @SerializedName("rover_id") val roverId: String?,
    @SerializedName("full_name") val fullName: String?
)
