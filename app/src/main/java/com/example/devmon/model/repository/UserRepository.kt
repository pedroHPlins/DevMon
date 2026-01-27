package com.example.devmon.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.devmon.model.domain.User
import com.example.devmon.model.domain.Creature
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val creaturesRepository : CreaturesRepository,) {

    val user = User("Pedro", true)

    private val _onChooseCreature = MutableLiveData<Creature>()
    val onChooseCreature: LiveData<Creature>
        get() = _onChooseCreature

    fun chooseCreature(){
        if(!user.hasCreatureAvailable){
         return
        }

        user.hasCreatureAvailable = false

        val randomCreature = creaturesRepository.creatures.random()
        user.creatures.add(randomCreature)

        _onChooseCreature.value = randomCreature
    }



}