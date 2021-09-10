package com.silva021.sorteio.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.sorteio.home.domain.model.Raffle
import com.silva021.sorteio.home.domain.usecase.DeleteRaffleParticipantUseCase
import com.silva021.sorteio.home.domain.usecase.GetRaffleUseCase
import com.silva021.sorteio.home.domain.usecase.InsertRaffleUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRaffleUseCase: GetRaffleUseCase,
    private val deleteRaffleParticipantUseCase: DeleteRaffleParticipantUseCase,
    private val insertRaffleUseCase: InsertRaffleUseCase,
) : ViewModel() {
    private val _rafflesList = MutableLiveData<List<Raffle>?>()
    val rafflesList = _rafflesList as LiveData<List<Raffle>?>

    private val _raffleDeleted = MutableLiveData<Boolean>()
    val raffleDeleted = _raffleDeleted as LiveData<Boolean>

    fun getRaffles(raffleType: String) {
        viewModelScope.launch {
            val raffles = getRaffleUseCase(raffleType)
            _rafflesList.value = raffles
        }
    }

    fun deleteRaffleParticipant(raffleType: String, raffle: Raffle) {
        val deleteRaffleParticipant = deleteRaffleParticipantUseCase(raffleType, raffle)
        _raffleDeleted.value = deleteRaffleParticipant
    }

    fun insertRaffles(raffle: List<Raffle>) {
        insertRaffleUseCase(raffle)
    }
}