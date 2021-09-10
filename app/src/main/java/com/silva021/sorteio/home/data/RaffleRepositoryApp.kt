package com.silva021.sorteio.home.data

import androidx.lifecycle.MutableLiveData
import com.silva021.sorteio.home.data.dao.RaffleDao
import com.silva021.sorteio.home.domain.model.Raffle
import com.silva021.sorteio.home.domain.model.RaffleFactory
import java.lang.Exception

class RaffleRepositoryApp(private val raffleDao: RaffleDao) : RaffleRepository {
    override suspend fun getRaffle(typeRaffle: String): List<Raffle> {
        return raffleDao.loadRafflesByType(typeRaffle)
    }

    override fun deleteRaffleParticipant(
        typeRaffle: String,
        raffle: Raffle,
    ): MutableLiveData<Boolean> {
        val raffleParticipantDelete = MutableLiveData<Boolean>()
//        myRef.child(typeRaffle).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                dataSnapshot.children.forEach { it ->
//                    val dataSnapshotRaffle = it.getValue(Raffle::class.java)
//                    dataSnapshotRaffle?.let { raffle ->
//                        if (raffle.title.equals(raffle.title)) {
//                            raffleParticipantDelete.value = myRef.child(typeRaffle).setValue(listOf(raffle)).isSuccessful
//                        }
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
        return raffleParticipantDelete
    }

    override fun insertRaffle(raffle: List<Raffle>): Boolean {
        return try {
            raffleDao.insertAll(raffle)
            true
        } catch (e: Exception) {
            false
        }
    }
}

interface RaffleRepository {
    suspend fun getRaffle(typeRaffle: String): List<Raffle>
    fun deleteRaffleParticipant(typeRaffle: String, raffle: Raffle): MutableLiveData<Boolean>
    fun insertRaffle(raffle: List<Raffle>): Boolean
}