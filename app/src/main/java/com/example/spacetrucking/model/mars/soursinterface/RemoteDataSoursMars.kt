package com.example.spacetrucking.model.mars.soursinterface

import com.example.spacetrucking.model.mars.data.PODServerResponseMarsData
import retrofit2.Callback

interface RemoteDataSoursMars {
    fun getDataFromMars(callback: Callback<PODServerResponseMarsData>)
}