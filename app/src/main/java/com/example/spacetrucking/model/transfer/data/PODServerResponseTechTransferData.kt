package com.example.spacetrucking.model.transfer.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PODServerResponseTechTransferData(@SerializedName("results") val results: List<List<String>>) : Serializable
