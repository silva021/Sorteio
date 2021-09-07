package com.silva021.sorteio.home.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
        Log.d("navigation_log", "homeFragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreen()
    }

    private fun setupScreen() {
        with(binding) {
            val raffleType = arguments?.getInt("raffle_type") ?: RaffleType.RAFFLE_ONE.ordinal

            val listRaffle = if (raffleType == RaffleType.RAFFLE_TWO.ordinal)
                RaffleFactory.listRaffleTwo
            else
                RaffleFactory.listRaffle

            recycler.adapter = RaffleAdapter(list = listRaffle) {
                showBottomSheets(it)
            }
        }
    }

    private fun showBottomSheets(raffle: Raffle) {
        RaffleDialogFragment.build {
            this.raffle = raffle
        }.show(requireActivity().supportFragmentManager, "")
    }

    override fun onStop() {
        Log.d("navigation_log", "homeFragment - stop")
        super.onStop()
    }

    override fun onPause() {
        Log.d("navigation_log", "homeFragment - pause ")
        super.onPause()
    }
}