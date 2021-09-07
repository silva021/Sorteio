package com.silva021.sorteio.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.silva021.sorteio.R
import com.silva021.sorteio.databinding.ActivityMainBinding
import com.silva021.sorteio.home.domain.RaffleType

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        changeBottomNavigationFragment()
    }


    private fun changeBottomNavigationFragment() {
        binding.mainBottombarNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_raffle_one -> {
                    navigateToHome(RaffleType.RAFFLE_ONE.ordinal)
                    true
                }
                R.id.navigation_raffle_two -> {
                    navigateToHome(RaffleType.RAFFLE_TWO.ordinal)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToHome(ordinalRaffleType: Int){
        val bundle = bundleOf("raffle_page" to ordinalRaffleType)
        findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment, bundle)
    }

}