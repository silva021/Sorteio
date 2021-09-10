package com.silva021.sorteio.home.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.silva021.sorteio.home.domain.model.Raffle

@Database(entities = [Raffle::class], version = 1, exportSchema = false)
abstract class RaffleDatabaseApp : RoomDatabase() {
    abstract fun raffleDao(): RaffleDao
}