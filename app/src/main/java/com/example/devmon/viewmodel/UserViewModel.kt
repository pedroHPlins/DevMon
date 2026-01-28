package com.example.devmon.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.devmon.extensions.rx.CompositeDisposableExtensions.plusAssign
import com.example.devmon.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    val user = userRepository.user
    val onChooseCreature = userRepository.onChooseCreature
    private val composite = CompositeDisposable()

    fun chooseCreature(){
        composite += userRepository.chooseCreature().subscribe{
            Log.d("CHOOSE_CREATURE", it.toString())
        }
    }

    override fun onCleared() {
        composite.dispose()
    }

}