package com.silva021.sorteio.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silva021.sorteio.databinding.FragmentHomeBinding
import com.silva021.sorteio.home.domain.Raffle
import com.silva021.sorteio.home.domain.RaffleFactory
import com.silva021.sorteio.home.domain.RaffleType

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val raffleType = arguments?.getInt("raffle_page")
            var list = listOf<Raffle>()
            if (raffleType != null) {
                list = if (raffleType == RaffleType.RAFFLE_TWO.ordinal)
                    RaffleFactory.listRaffle
                else
                    RaffleFactory.listRaffleTwo
            }
            recycler.adapter = RaffleAdapter(list = list) {
                showBottomSheets(it)
            }
        }
    }

    private fun showBottomSheets(raffle: Raffle) {
        RaffleDialogFragment.build {
            this.raffle = raffle
        }.show(requireActivity().supportFragmentManager, "")
    }

}