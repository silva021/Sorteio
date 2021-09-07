package com.silva021.sorteio.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silva021.sorteio.databinding.ItemRaffleTicketBinding
import com.silva021.sorteio.home.domain.Raffle

typealias RaffleItemListener = (Raffle) -> Unit


class RaffleAdapter(
    private val list: List<Raffle>,
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
}

class RaffleViewHolder(
    private val binding: ItemRaffleTicketBinding,
    private val onClickListener: RaffleItemListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(raffle: Raffle) {
        with(binding) {
            textTitleRaffle.text = raffle.title
            cardRoot.setOnClickListener {
                onClickListener(raffle)
            }

        }
    }
}