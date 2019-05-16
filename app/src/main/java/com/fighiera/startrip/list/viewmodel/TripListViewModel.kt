package com.fighiera.startrip.list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.usecases.TripListUseCase
import com.fighiera.startrip.common.entities.Status
import com.fighiera.startrip.common.tools.SingleLiveEvent
import com.fighiera.startrip.list.entities.TripListItemUi
import com.fighiera.startrip.list.mapper.TripListMapper
import kotlinx.coroutines.*

class TripListViewModel(private val useCase: TripListUseCase) : ViewModel() {

    private val _tripEntries = MutableLiveData<List<TripListItemUi>>()
    private val _state = MutableLiveData<Status>()
    private val _displayDetail = SingleLiveEvent<Int>()

    val tripEntries: LiveData<List<TripListItemUi>>
        get() = _tripEntries
    val state: LiveData<Status>
        get() = _state
    val displayDetail: LiveData<Int>
        get() = _displayDetail


    private val viewModelJob = Job()
    private val fetchErrorHandler = CoroutineExceptionHandler { _, throwable -> onFetchError(throwable) }
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob + fetchErrorHandler)

    init {
        fetchList()
    }

    fun fetchList() {
        viewModelScope.launch {
            _state.value = Status.LOADING
            val tripList = withContext(Dispatchers.IO) { useCase.getTripList() }
            onFetchSuccess(tripList)
        }
    }

    private fun onFetchSuccess(tripList: TripListDomain) {
        _tripEntries.value = TripListMapper()(tripList)
        _state.value = Status.IDLE
    }

    private fun onFetchError(throwable: Throwable?) {
        Log.e("Error", throwable?.message)
        _state.value = Status.ERROR
    }

    fun displayTrip(itemId: Int) {
        _displayDetail.value = itemId
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}
