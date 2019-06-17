package com.fighiera.startrip.detail

import com.fighiera.domain.usecases.DetailUseCase
import com.fighiera.startrip.detail.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DetailModules {

    fun getModule() = module {
        scope(named<DetailActivity>()) {
            viewModel { (id: Int) -> DetailViewModel(id, useCase = get()) }
            scoped { DetailUseCase(tripRepository = get()) }
        }
    }
}