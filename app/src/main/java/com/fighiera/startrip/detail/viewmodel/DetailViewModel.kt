package com.fighiera.startrip.detail.viewmodel

import android.util.Log
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

    val trip = MutableLiveData<DetailItem>()
    val header = MutableLiveData<DetailHeaderItem>()
    val state = MutableLiveData<Status>()
    private var disposable: Disposable? = null

    init {
        fetchList()
    }

    fun fetchList() {
        disposable?.dispose()
        disposable = useCase.getTrip(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = Status.LOADING }
            .subscribe(::onFetchSuccess, ::onFetchError)
    }

    private fun onFetchSuccess(detail: TripItemDomain) {
        trip.value = DetailItemMapper()(detail)
        header.value = DetailHeaderMapper()(detail)
        state.value = Status.IDLE
    }

    private fun onFetchError(throwable: Throwable?) {
        Log.e("Error", throwable?.message)
        state.value = Status.ERROR
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}