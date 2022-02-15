package com.example.spacetrucking.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.main.data.PictureOfTheDayState
import com.example.spacetrucking.model.main.datasource.RemoteDataSourceImpl
import com.example.spacetrucking.model.main.repository.RepositoryPictureEmpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val liveStateForViewToObserve: MutableLiveData<PictureOfTheDayState> =
        MutableLiveData(),
    private val repositoryPicture: RepositoryPictureEmpl = RepositoryPictureEmpl(
        RemoteDataSourceImpl()
    )
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getData(): LiveData<PictureOfTheDayState> {
        sendServerRequest()
        return liveStateForViewToObserve
    }

    private fun sendServerRequest() {
        liveStateForViewToObserve.value = PictureOfTheDayState.Loading(2)

        compositeDisposable.add(
            repositoryPicture.getWeatherDataFromServers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveStateForViewToObserve.value = PictureOfTheDayState.Success(it)
                }, {
                    liveStateForViewToObserve.value = PictureOfTheDayState.Error(it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}