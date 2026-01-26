package com.example.devmon

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigation()
    }

    private fun initNavigation() {
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph
    }

}