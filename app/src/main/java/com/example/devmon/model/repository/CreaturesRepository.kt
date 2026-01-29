package com.example.devmon.model.repository

import android.util.Log
import com.example.devmon.model.domain.Creature
import com.example.devmon.model.source.remote.CreatureRemoteDataSource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.ReplaySubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreaturesRepository @Inject constructor(remoteDataSource: CreatureRemoteDataSource) {
    val creatures: ReplaySubject<List<Creature>> = ReplaySubject.create(1)

    init {
        remoteDataSource.creatures
            .doOnNext { Log.d("CREATURES_REPOSITORY", "${it.size} creatures were loaded from API")
            }
            .doOnError { Log.e("CREATURES_REPOSITORY", "Error loading creatures from API.",it)
            }
            .subscribe{creatures.onNext(it)
            }
    }

    fun findCreature(number: Int): Observable<Creature> =
        creatures.map {list ->
            list.find { it.number == number }!!
        }
}