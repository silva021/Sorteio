package com.silva021.sorteio.home.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silva021.sorteio.databinding.ItemRaffleTicketBinding
import com.silva021.sorteio.home.domain.model.Raffle

typealias RaffleItemListener = (Raffle) -> Unit

class RaffleAdapter(
    private var list: List<Raffle>,
    private val onClickListener: RaffleItemListener,
) : RecyclerView.Adapter<RaffleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RaffleViewHolder(
        ItemRaffleTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onClickListener
    )

    override fun onBindViewHolder(holder: RaffleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateRaffleCard(list: List<Raffle>) {
        this.list = list
        notifyDataSetChanged()
    }
}

class RaffleViewHolder(
    private val binding: ItemRaffleTicketBinding,
    private val onClickListener: RaffleItemListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(raffle: Raffle) {
        with(binding) {
            textTitleRaffle.text = raffle.title
            raffle.participantName?.let {
                disableCard()
            }
            cardRoot.setOnClickListener {
                onClickListener(raffle)
            }

        }
    }

    private fun disableCard() {
        with(binding) {
            cardRoot.setBackgroundColor(Color.parseColor("#FF03DAC5"))
            textTitleRaffle.setTextColor(Color.WHITE)
        }
    }
}