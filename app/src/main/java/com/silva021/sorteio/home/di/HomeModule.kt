package com.silva021.sorteio.home.di

import android.app.Application
import androidx.room.Room
import com.silva021.sorteio.home.data.RaffleRepository
import com.silva021.sorteio.home.data.RaffleRepositoryApp
import com.silva021.sorteio.home.data.dao.RaffleDatabaseApp
import com.silva021.sorteio.home.data.dao.RaffleDao
import com.silva021.sorteio.home.domain.usecase.DeleteRaffleParticipant
import com.silva021.sorteio.home.domain.usecase.DeleteRaffleParticipantUseCase
import com.silva021.sorteio.home.domain.usecase.GetRaffle
import com.silva021.sorteio.home.domain.usecase.GetRaffleUseCase
import com.silva021.sorteio.home.domain.usecase.InsertRaffle
import com.silva021.sorteio.home.domain.usecase.InsertRaffleUseCase
import com.silva021.sorteio.home.ui.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val homeModule = module {
    single { providerRaffleDatabase(androidApplication()) }
    single { providerRaffleDao(get()) }

    single { HomeViewModel(getRaffleUseCase = get(), deleteRaffleParticipantUseCase = get(), insertRaffleUseCase = get()) }
    single<GetRaffleUseCase> { GetRaffle(repository = get()) }
    single<DeleteRaffleParticipantUseCase> { DeleteRaffleParticipant(repository = get()) }
    single<InsertRaffleUseCase> { InsertRaffle(repository = get()) }
    single<RaffleRepository> { RaffleRepositoryApp(raffleDao = get()) }
//    single<RaffleRepository> { RaffleRepositoryApp() }

}

fun providerRaffleDatabase(application: Application): RaffleDatabaseApp {
    return Room.databaseBuilder(
        application,
        RaffleDatabaseApp::class.java,
        "raffle-database")
        .allowMainThreadQueries()
        .build()
}

fun providerRaffleDao(databaseApp: RaffleDatabaseApp): RaffleDao {
    return databaseApp.raffleDao()
}