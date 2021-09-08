package com.silva021.sorteio.home.di

import com.silva021.sorteio.home.data.RaffleRepository
import com.silva021.sorteio.home.data.RaffleRepositoryApp
import com.silva021.sorteio.home.domain.usecase.GetRaffle
import com.silva021.sorteio.home.domain.usecase.GetRaffleUseCase
import com.silva021.sorteio.home.ui.HomeViewModel
import org.koin.dsl.module

val homeModule = module {
    single { HomeViewModel(getRaffleUseCase = get()) }
    single<GetRaffleUseCase> { GetRaffle(repository = get()) }
    single<RaffleRepository> { RaffleRepositoryApp() }
}
