package com.example.spacetrucking.model.inter


interface RepositoryInter {
    fun <T> getMediaFromServer(callback: retrofit2.Callback<T>)
}