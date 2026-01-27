package com.example.devmon.view.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.devmon.R
import com.example.devmon.viewmodel.NavigationViewModel
import com.example.devmon.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.devmon.view.creatures.CreatureListFragmentDirections
import com.example.devmon.view.creatures.CreatureChooseFragmentDirections

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()

        userViewModel.onChooseCreature.observe(this){
            if(navController.currentDestination?.id != R.id.creature_added_dest){
                val action = CreatureChooseFragmentDirections.creatureChooseToCreatureAddedAction(it.number)
                navController.navigate(action)
            }
        }
    }

    private fun initNavigation() {
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph

        if (userViewModel.user.hasCreatureAvailable
            && navController.currentDestination?.id !=
            R.id.creature_choose_dest
        ) {
            val action = CreatureListFragmentDirections.creatureChooseAction()
            navController.navigate(action)
        }

    }

}