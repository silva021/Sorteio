package com.silva021.sorteio.home.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Raffle {
    lateinit var title: String
    var participant: Participant? = null

    constructor()

    constructor(title: String, participant: Participant?) {
        this.title = title
        this.participant = participant
    }
}
