package hu.bme.aut.dogspecies.datasource.network

import hu.bme.aut.dogspecies.model.BreedDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedApi {
    @GET("/breeds")
    suspend fun getBreeds(): List<BreedDto>

    @GET("/breeds/{id}")
    suspend fun getBreed(@Path("id") id: Int): BreedDto
}