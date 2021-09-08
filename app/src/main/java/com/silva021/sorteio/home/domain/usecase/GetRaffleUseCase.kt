package com.silva021.sorteio.home.domain.usecase

import androidx.lifecycle.LiveData
import com.silva021.sorteio.home.data.RaffleRepository
import com.silva021.sorteio.home.domain.model.Raffle

interface GetRaffleUseCase {
    operator fun invoke(typeRaffle: String): LiveData<List<Raffle>>
}

class GetRaffle(private val repository: RaffleRepository) : GetRaffleUseCase {
    override fun invoke(typeRaffle: String): LiveData<List<Raffle>> {
        return repository.getRaffle(typeRaffle)
    }

}