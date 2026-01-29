package com.example.devmon.model.source.remote.services

import com.example.devmon.model.source.remote.entity.CreatureApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CreatureService {
    @GET("creature")
    fun findAll() : Observable<List<CreatureApiResponse>>
}