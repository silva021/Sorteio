package com.silva021.sorteio.home.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Participant {
    lateinit var name: String
    var contact: String? = null

    constructor()

    constructor(name: String, contact: String?) {
        this.name = name
        this.contact = contact
    }
}
