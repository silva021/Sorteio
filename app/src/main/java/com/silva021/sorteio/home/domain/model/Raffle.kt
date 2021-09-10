package com.silva021.sorteio.home.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "t_Raffle")
data class Raffle(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val raffleType: String,
    var title: String,
    var participantName: String? = null,
    var participantContact: String? = null
)
