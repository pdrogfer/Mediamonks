package com.pgf.mediamonks.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pgf.mediamonks.core.api.UseCaseGetPhotos
import com.pgf.mediamonks.core.api.model.PhotoItem
import com.pgf.mediamonks.core.exception.Failure
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val useCaseGetPhotos: UseCaseGetPhotos) : ViewModel() {

    var photos: MutableLiveData<List<PhotoItem>> = MutableLiveData()
    var failure: MutableLiveData<Failure> = MutableLiveData()

    fun getPhotos() {
        useCaseGetPhotos.invoke(UseCaseGetPhotos.Params("not used")) {
            it.either(::handleFailure, ::handlePhotosResults)
        }

    }

    private fun handlePhotosResults(listPhotos: List<PhotoItem>) {
        this.photos.value = listPhotos.subList(0, 10)
    }

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}
