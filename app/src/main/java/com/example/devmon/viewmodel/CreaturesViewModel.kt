package com.example.devmon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devmon.model.domain.Creature
import com.example.devmon.model.repository.CreaturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(private val
    creaturesRepository: CreaturesRepository
) : ViewModel() {
    val creatures = MutableLiveData<List<Creature>>()

    init {
        creatures.value = creaturesRepository.creatures
    }

    fun findCreature(number: Int) = creaturesRepository.findCreature(number)

}