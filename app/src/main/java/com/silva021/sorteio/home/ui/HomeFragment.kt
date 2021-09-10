package com.silva021.sorteio.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.silva021.sorteio.app.INITIALIZE_FLAG_KEY
import com.silva021.sorteio.app.dataStore
import com.silva021.sorteio.databinding.FragmentHomeBinding
import com.silva021.sorteio.home.domain.clicks.RaffleDialogClicks
import com.silva021.sorteio.home.domain.model.Raffle
import com.silva021.sorteio.home.domain.model.RaffleFactory
import com.silva021.sorteio.home.domain.model.RaffleType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by sharedViewModel()
    private val raffleType by lazy {
        arguments?.getString("raffle_type") ?: RaffleType.RAFFLE_ONE.name
    }
    private val raffleAdapter by lazy {
        RaffleAdapter(listOf()) {
            showBottomSheets(
                it,
                RaffleDialogClicks(onDialogClickPositiveListener, onDialogClickNegativeListener)
            )
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
        setupScreen()
        setupObserver()
        viewModel.getRaffles(raffleType)
    }

    private fun setupObserver() {
        viewModel.rafflesList.observe(viewLifecycleOwner, {
            it?.let {
                raffleAdapter.updateRaffleCard(it)
            }
        })

        viewModel.raffleDeleted.observe(viewLifecycleOwner, {
            it?.let {
                viewModel.getRaffles(raffleType)
            }
        })
    }

    private val onDialogClickPositiveListener: (Raffle) -> Unit = { raffle ->
        viewModel.deleteRaffleParticipant(raffleType, raffle)
    }

    private val onDialogClickNegativeListener: (Raffle) -> Unit = { raffle ->
        viewModel.deleteRaffleParticipant(raffleType, raffle)
    }

    private fun setupScreen() {
        setupAdapter()
        insertRaffleList()

        lifecycleScope.launch {
            context?.dataStore?.edit { pref ->
                pref[INITIALIZE_FLAG_KEY] = false
            }
        }
    }

    private fun insertRaffleList() {
        lifecycleScope.launch {
            val flowInitializeFlag = context?.dataStore?.data?.map { pref ->
                pref[INITIALIZE_FLAG_KEY] ?: true
            }
            flowInitializeFlag?.collect { flag ->
                if (flag) {
                    viewModel.insertRaffles(RaffleFactory.listRaffle)
                    viewModel.insertRaffles(RaffleFactory.listRaffleTwo)
                }
            }
        }
    }

    private fun setupAdapter() {
        with(binding) {
            recycler.adapter = raffleAdapter
        }
    }

    private fun showBottomSheets(raffle: Raffle, raffleDialogClicks: RaffleDialogClicks) {
        RaffleDialogFragment.build {
            this.raffle = raffle
            this.raffleDialogClicks = raffleDialogClicks
        }.show(requireActivity().supportFragmentManager, "")
    }
}