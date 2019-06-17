package com.fighiera.startrip.list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.usecases.TripListUseCase
import com.fighiera.startrip.common.entities.Status
import com.fighiera.startrip.common.tools.SingleLiveEvent
import com.fighiera.startrip.list.entities.TripListItemUi
import com.fighiera.startrip.list.mapper.TripListMapper
import kotlinx.coroutines.launch

class TripListViewModel(private val useCase: TripListUseCase) : ViewModel() {

    private val _tripEntries = MutableLiveData<List<TripListItemUi>>()
    private val _state = MutableLiveData<Status>()
    private val _displayTrip = SingleLiveEvent<Int>()

    val tripEntries: LiveData<List<TripListItemUi>>
        get() = _tripEntries
    val state: LiveData<Status>
        get() = _state
    val displayTrip: LiveData<Int>
        get() = _displayTrip

    fun fetchList() {
        if(_tripEntries.value != null){
            return
        }

        viewModelScope.launch {
            try {
                _state.value = Status.LOADING
                val tripList = useCase.getTripList()
                onFetchSuccess(tripList)
            }catch (throwable : Throwable){
                onFetchError(throwable)
            }
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
        _displayTrip.value = itemId
    }
}
