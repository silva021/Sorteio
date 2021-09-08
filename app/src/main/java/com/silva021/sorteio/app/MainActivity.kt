package com.silva021.sorteio.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.silva021.sorteio.R
import com.silva021.sorteio.databinding.ActivityMainBinding
import com.silva021.sorteio.home.domain.model.RaffleType

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        splashLoading()
        changeBottomNavigationFragment()
    }

    private fun splashLoading() {
        with(binding) {
            animationView.addAnimatorUpdateListener {
                it.doOnEnd {
                    animationView.pauseAnimation()
                    animationView.visibility = View.GONE
                    containerFragment.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun changeBottomNavigationFragment() {
        binding.mainBottombarNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_raffle_one -> {
                    navigateToHome(RaffleType.RAFFLE_ONE.name)
                    true
                }
                R.id.navigation_raffle_two -> {
                    navigateToHome(RaffleType.RAFFLE_TWO.name)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToHome(raffleType: String) {
        val bundle = bundleOf("raffle_type" to raffleType)
        with(findNavController(R.id.nav_host_fragment)) {
            popBackStack()
            navigate(R.id.homeFragment, bundle)
        }
    }

}