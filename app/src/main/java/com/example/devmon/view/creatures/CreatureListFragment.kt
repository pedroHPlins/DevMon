package com.example.devmon.view.creatures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devmon.R
import com.example.devmon.viewmodel.CreaturesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatureListFragment : Fragment() {
    private val creaturesViewModel: CreaturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.creatures_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        creaturesViewModel.creatures.observe(viewLifecycleOwner) {
            recyclerView.adapter = CreatureListAdapter(it)
        }
    }
}