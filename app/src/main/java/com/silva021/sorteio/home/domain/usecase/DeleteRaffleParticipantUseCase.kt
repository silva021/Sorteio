package com.silva021.sorteio.home.domain.usecase


import androidx.lifecycle.LiveData
import com.silva021.sorteio.home.data.RaffleRepository
import com.silva021.sorteio.home.domain.model.Raffle
import java.lang.Exception

interface DeleteRaffleParticipantUseCase {
    operator fun invoke(typeRaffle: String, raffle: Raffle): Boolean
}

class DeleteRaffleParticipant(private val repository: RaffleRepository) :
    DeleteRaffleParticipantUseCase {
    override fun invoke(typeRaffle: String, raffle: Raffle): Boolean {
        return try {
            repository.insertRaffle(
                listOf(
                    Raffle(
                        raffle.uid,
                        raffle.raffleType,
                        raffle.title,
                        null,
                        null)
                )
            )
            true
        } catch (e: Exception) {
            false
        }
    }

}