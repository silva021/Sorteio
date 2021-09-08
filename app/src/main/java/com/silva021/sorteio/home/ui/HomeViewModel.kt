package com.silva021.sorteio.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.sorteio.home.domain.model.Raffle
import com.silva021.sorteio.home.domain.usecase.GetRaffleUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getRaffleUseCase: GetRaffleUseCase) : ViewModel() {
    private val _rafflesList = MutableLiveData<List<Raffle>?>()
    val rafflesList = _rafflesList as LiveData<List<Raffle>?>

    fun getRaffles(raffleType: String) {
        viewModelScope.launch {

            val extract = getRaffleUseCase(raffleType)

            extract.observeForever {
                _rafflesList.value = it
            }
        }
    }
}