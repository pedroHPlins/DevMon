package com.example.devmon.view.creatures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.devmon.databinding.CreatureViewFragmentBinding
import com.example.devmon.viewmodel.CreatureViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatureViewFragment: Fragment() {

    private lateinit var binding : CreatureViewFragmentBinding

    private val viewModel : CreatureViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CreatureViewFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: CreatureViewFragmentArgs by navArgs()
        val creatureId = safeArgs.creatureNumber

        viewModel.loadCreature(creatureId)
        binding.viewModel = viewModel
    }
}