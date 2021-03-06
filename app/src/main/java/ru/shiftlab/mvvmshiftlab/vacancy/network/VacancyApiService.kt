package ru.shiftlab.mvvmshiftlab.vacancy.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface VacancyApiService {

    @GET("vacancy")
    fun getVacanciesAsync(): Deferred<List<VacancyNetworkEntity>>

    @GET("vacancy/{id}")
    fun getVacancyById(@Path("id") id: Int): Deferred<VacancyNetworkEntity>
}