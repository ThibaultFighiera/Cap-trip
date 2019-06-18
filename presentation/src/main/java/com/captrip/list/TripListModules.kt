package com.captrip.list

import com.captrip.domain.usecases.TripListUseCase
import com.captrip.list.viewmodel.TripListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object TripListModules {

    fun getModule() = module {
        scope(named<TripListActivity>()) {
            viewModel { TripListViewModel(useCase = get()) }
            scoped { TripListUseCase(tripRepository = get()) }
        }
    }
}