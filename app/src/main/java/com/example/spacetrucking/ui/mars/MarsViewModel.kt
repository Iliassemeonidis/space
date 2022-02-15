package com.example.spacetrucking.ui.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.model.mars.repository.RepositoryMarsEmpl
import com.example.spacetrucking.model.mars.soursinterface.RemoteDataSoursMarsImpl
import com.example.spacetrucking.model.mars.state.PictureOfTheMars
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MarsViewModel(
    private val liveDataForViewToObserve: MutableLiveData<PictureOfTheMars> =
        MutableLiveData(),
    private val repositoryPicture: RepositoryMarsEmpl = RepositoryMarsEmpl(
        RemoteDataSoursMarsImpl()
    )
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getData(): LiveData<PictureOfTheMars> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = PictureOfTheMars.Loading(2)
        compositeDisposable.add(
        repositoryPicture.getDataMarsFromServers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataForViewToObserve.value = PictureOfTheMars.Success(it)
            },{
                liveDataForViewToObserve.value = PictureOfTheMars.Error(it)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}