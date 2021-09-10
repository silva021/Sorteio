package com.silva021.sorteio.home.domain.usecase

import androidx.lifecycle.LiveData
import com.silva021.sorteio.home.data.RaffleRepository
import com.silva021.sorteio.home.domain.model.Raffle

interface GetRaffleUseCase {
    suspend operator fun invoke(typeRaffle: String): List<Raffle>
}

class GetRaffle(private val repository: RaffleRepository) : GetRaffleUseCase {
    override suspend fun invoke(typeRaffle: String): List<Raffle> {
        return repository.getRaffle(typeRaffle)
    }
}