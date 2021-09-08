package com.silva021.sorteio.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.silva021.sorteio.app.enable
import com.silva021.sorteio.databinding.RaffleDialogLayoutBinding
import com.silva021.sorteio.home.domain.model.Raffle

class RaffleDialogFragment(
    private val raffle: Raffle
) : BottomSheetDialogFragment() {
    var binding: RaffleDialogLayoutBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = RaffleDialogLayoutBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind()
    }

    private fun bind() {
        with(binding!!) {
            textParticipation.text = raffle.title

            raffle.participant?.let {
                textValueContact.text = it.contact
                textValueName.text = it.name
                buttonNegative.enable("#BD1919")
            }
        }
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        lateinit var raffle: Raffle

        fun build() = RaffleDialogFragment(raffle)
    }
}
