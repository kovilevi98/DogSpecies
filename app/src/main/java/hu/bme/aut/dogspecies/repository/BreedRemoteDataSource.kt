package hu.bme.aut.dogspecies.repository

import hu.bme.aut.dogspecies.datasource.network.BreedApi
import hu.bme.aut.dogspecies.model.toBreed
import javax.inject.Inject

class BreedRemoteDataSource  @Inject constructor(
    private val breedApi: BreedApi
) {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    suspend fun getBreeds() = safeApiCall {
        breedApi.getBreeds().map { it.toBreed() }
    }

    suspend fun getCharacter(id: Int) = safeApiCall {
        breedApi.getBreed(id).toBreed()
    }
}