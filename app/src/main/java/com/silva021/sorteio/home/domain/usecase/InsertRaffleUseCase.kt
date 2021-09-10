package com.silva021.sorteio.home.domain.usecase

import com.silva021.sorteio.home.data.RaffleRepository
import com.silva021.sorteio.home.domain.model.Raffle

interface InsertRaffleUseCase {
    operator fun invoke(list: List<Raffle>): Boolean
}

class InsertRaffle(private val repository: RaffleRepository) : InsertRaffleUseCase {
    override fun invoke(list: List<Raffle>): Boolean {
        return repository.insertRaffle(list)
    }
}