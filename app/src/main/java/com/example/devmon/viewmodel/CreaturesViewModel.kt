package com.example.devmon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devmon.model.repository.CreaturesRepository
import com.example.devmon.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(private val
    creaturesRepository: CreaturesRepository,
    userRepository: UserRepository,
) : ViewModel() {
    val creatures = MutableLiveData(userRepository.allCreatures)

    fun findCreature(number: Int) = creaturesRepository.findCreature(number)

}