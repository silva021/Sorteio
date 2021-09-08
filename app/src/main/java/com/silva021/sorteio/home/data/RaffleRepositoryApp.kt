package com.silva021.sorteio.home.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.silva021.sorteio.home.domain.model.Raffle

class RaffleRepositoryApp : RaffleRepository {
    lateinit var myRef: DatabaseReference

    override fun getRaffle(typeRaffle: String): MutableLiveData<List<Raffle>> {
        myRef = Firebase.database.getReference("raffles")
        val getRaffle = MutableLiveData<List<Raffle>>()
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<Raffle>()
                dataSnapshot.child(typeRaffle).children.forEach {
                    val dataSnapshotRaffle = it.getValue(Raffle::class.java)
                    dataSnapshotRaffle?.let { raffle ->
                        list.add(raffle)
                    }
                }
                getRaffle.value = list
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return getRaffle
    }
}

interface RaffleRepository {
    fun getRaffle(typeRaffle: String): MutableLiveData<List<Raffle>>
}