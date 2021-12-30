package com.example.spacetrucking.model.repository.marspicture.data

import com.google.gson.annotations.SerializedName

data class CameraData(val name: String?, @field:SerializedName("rover_id") val roverId: String?, val fullName: String?)
