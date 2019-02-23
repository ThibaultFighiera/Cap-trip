package com.fighiera.startrip.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.usecases.TripListUseCase
import com.fighiera.startrip.common.tools.SingleLiveEvent
import com.fighiera.startrip.common.entities.Status
import com.fighiera.startrip.list.entities.TripListItemUi
import com.fighiera.startrip.list.mapper.TripListMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TripListViewModel(private val useCase: TripListUseCase) : ViewModel() {
    val tripEntries = MutableLiveData<List<TripListItemUi>>()
    val state = MutableLiveData<Status>()
    val displayDetail = SingleLiveEvent<Int>()
    private var disposable: Disposable? = null

    init {
        fetchList()
    }

    fun fetchList() {
        disposable?.dispose()
        disposable = useCase.getTripList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { state.value = Status.LOADING }
            .subscribe(::onFetchSuccess, ::onFetchError)
    }

    private fun onFetchSuccess(tripList: TripListDomain) {
        tripEntries.value = TripListMapper()(tripList)
        state.value = Status.IDLE
    }

    private fun onFetchError(throwable: Throwable?) {
        Log.e("Error", throwable?.message)
        state.value = Status.ERROR
    }

    fun displayTrip(itemId: Int) {
        displayDetail.value = itemId
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}

class TripListViewModelFactory(private val useCase: TripListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(p0: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return TripListViewModel(useCase) as T
    }
}