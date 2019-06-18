package com.captrip.detail

import com.captrip.domain.usecases.DetailUseCase
import com.captrip.detail.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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