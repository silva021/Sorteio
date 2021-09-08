package com.silva021.sorteio.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.silva021.sorteio.databinding.FragmentHomeBinding
import com.silva021.sorteio.home.domain.model.Raffle
import com.silva021.sorteio.home.domain.model.RaffleFactory
import com.silva021.sorteio.home.domain.model.RaffleType
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by sharedViewModel()
    val raffleAdapter by lazy {
        RaffleAdapter(listOf()) {
            showBottomSheets(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val raffleType = arguments?.getString("raffle_type") ?: RaffleType.RAFFLE_ONE.name
        viewModel.getRaffles(raffleType)
        setupScreen()
        setupObserver()

        val database = Firebase.database
        val myRef = database.getReference("raffles")
        myRef.child(RaffleType.RAFFLE_ONE.name).setValue(RaffleFactory.listRaffle)
        myRef.child(RaffleType.RAFFLE_TWO.name).setValue(RaffleFactory.listRaffleTwo)
    }

    private fun setupObserver() {
        viewModel.rafflesList.observe(viewLifecycleOwner, {
            it?.let {
                raffleAdapter.updateRaffleCard(it)
            }
        })
    }

    private fun setupScreen() {
        setupAdapter()
    }

    private fun setupAdapter() {
        with(binding) {
            recycler.adapter = raffleAdapter
        }
    }

    private fun showBottomSheets(raffle: Raffle) {
        RaffleDialogFragment.build {
            this.raffle = raffle
        }.show(requireActivity().supportFragmentManager, "")
    }
}