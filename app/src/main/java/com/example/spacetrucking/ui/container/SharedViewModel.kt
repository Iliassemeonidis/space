package com.example.spacetrucking.ui.container

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacetrucking.ui.SpaceTab

class SharedViewModel(private var _liveDatta: MutableLiveData<SpaceTab> = MutableLiveData()) :
    ViewModel() {

    private val liveDataToObserve: LiveData<SpaceTab>
        get() = _liveDatta

    fun setSpaceTab(spaceTab: SpaceTab) {
        _liveDatta.value = spaceTab
    }

    fun subscribeToLiveData(): LiveData<SpaceTab> {
        return liveDataToObserve
    }
}