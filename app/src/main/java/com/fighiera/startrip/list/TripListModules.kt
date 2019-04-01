package com.fighiera.startrip.list

import com.fighiera.domain.usecases.TripListUseCase
import com.fighiera.startrip.list.viewmodel.TripListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object TripListModules {

    fun get() = module {
        scope(named<TripListActivity>()) {
            viewModel { TripListViewModel(useCase = get()) }
            scoped { TripListUseCase(tripRepository = get()) }
        }
    }
}