package com.example.devmon.model.repository

import android.R.attr.name
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

    val allCreatures
        get() = creaturesRepository.creatures.map{
            val isKnown = user.creatures.any{creatureOwnByUser -> creatureOwnByUser.number == it.number}
            it.copy(name = if(isKnown) it.name else "?????",
                isKnown = isKnown)
        }


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