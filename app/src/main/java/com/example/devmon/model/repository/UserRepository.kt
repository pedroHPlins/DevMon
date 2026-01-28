package com.example.devmon.model.repository

import android.R.attr.name
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.devmon.model.domain.User
import com.example.devmon.model.domain.Creature
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val creaturesRepository : CreaturesRepository,) {

    val user = User("Pedro", true)

    private val _onChooseCreature = MutableLiveData<Creature>()
    val onChooseCreature: LiveData<Creature>
        get() = _onChooseCreature

    val allCreatures: Observable<List<Creature>>
        get() = creaturesRepository.creatures.map{
            list -> list.map {
                val isKnown = user.creatures.any { creatureOwnByUser ->
                    creatureOwnByUser.number == it.number
                }

                it.copy(
                    name = if (isKnown) it.name else "?????",
                    isKnown = isKnown
                )
            }
        }


    fun chooseCreature(): Observable<Creature> =
        Observable.just(user)
            .filter {
                it.hasCreatureAvailable
            }
            .doOnNext {
                it.hasCreatureAvailable = false
            }
            .flatMap {
                creaturesRepository.creatures
            }
            .map {
                val randomCreature = it.random()
                randomCreature
            }
            .doOnNext {
                user.creatures.add(it)
            }
            .doOnNext {
                _onChooseCreature.postValue(it)
            }
}