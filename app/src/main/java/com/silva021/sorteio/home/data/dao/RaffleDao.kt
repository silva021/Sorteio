package com.silva021.sorteio.home.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.silva021.sorteio.home.domain.model.Raffle

@Dao
interface RaffleDao {
    @Query("SELECT * FROM t_Raffle")
    fun getAll(): List<Raffle>

    @Query("SELECT * FROM t_Raffle WHERE raffleType = (:raffleType)")
    fun loadRafflesByType(raffleType: String): List<Raffle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(raffles: List<Raffle>)
}