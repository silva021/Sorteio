package com.silva021.sorteio.home.domain.clicks

import com.silva021.sorteio.home.domain.model.Raffle

typealias ButtonListener = (Raffle) -> Unit

data class RaffleDialogClicks(
    val clickPositiveListener: ButtonListener,
    val clickNegativeListener: ButtonListener
)