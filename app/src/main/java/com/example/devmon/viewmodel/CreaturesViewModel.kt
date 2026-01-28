package com.example.devmon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import com.example.devmon.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.devmon.model.domain.Creature
import com.example.devmon.model.repository.CreaturesRepository
import com.example.devmon.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(private val
    creaturesRepository: CreaturesRepository,
    userRepository: UserRepository,
) : ViewModel() {

    val creatures = MutableLiveData<List<Creature>>()
    private val composite = CompositeDisposable()

    init{
        composite += userRepository.allCreatures.subscribe{creatures.value = it}
    }

    override fun onCleared() {
        composite.dispose()
    }

    fun findCreature(number: Int) = creaturesRepository.findCreature(number)

}