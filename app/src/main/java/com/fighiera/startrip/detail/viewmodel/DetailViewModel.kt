package com.fighiera.startrip.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.usecases.DetailUseCase
import com.fighiera.startrip.common.entities.Status
import com.fighiera.startrip.detail.mapper.DetailHeaderMapper
import com.fighiera.startrip.detail.mapper.DetailItemMapper
import com.fighiera.startrip.detail.model.DetailHeaderItem
import com.fighiera.startrip.detail.model.DetailItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val id: Int, private val useCase: DetailUseCase) : ViewModel() {

    private val _trip = MutableLiveData<DetailItem>()
    private val _header = MutableLiveData<DetailHeaderItem>()
    private val _state = MutableLiveData<Status>()

    val trip: LiveData<DetailItem>
        get() = _trip
    val header: LiveData<DetailHeaderItem>
        get() = _header
    val state: LiveData<Status>
        get() = _state

    private var disposable: Disposable? = null

    init {
        fetchList()
    }

    fun fetchList() {
        disposable?.dispose()
        disposable = useCase.getTrip(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _state.value = Status.LOADING }
            .subscribe(::onFetchSuccess, ::onFetchError)
    }

    private fun onFetchSuccess(detail: TripItemDomain) {
        _trip.value = DetailItemMapper()(detail)
        _header.value = DetailHeaderMapper()(detail)
        _state.value = Status.IDLE
    }

    private fun onFetchError(throwable: Throwable?) {
        Log.e("Error", throwable?.message)
        _state.value = Status.ERROR
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}