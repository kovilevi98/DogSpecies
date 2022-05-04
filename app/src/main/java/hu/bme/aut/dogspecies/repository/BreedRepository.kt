package hu.bme.aut.dogspecies.repository

import hu.bme.aut.dogspecies.model.Breed
import javax.inject.Inject

class BreedRepository @Inject
constructor(
    private val breedLocalDataSource: BreedLocalDataSource,
    private val breedRemoteDataSource: BreedRemoteDataSource
) {

    suspend fun getBreeds(): List<Breed> {
        breedRemoteDataSource.getBreeds().onSuccess {
            return it
        }.onFailure {
            return breedLocalDataSource.getBreeds()
        }
        return emptyList()
    }

    suspend fun getBreed(id: Int): Breed? {
        breedRemoteDataSource.getBreed(id).onSuccess {
            return it
        }.onFailure {
            return breedLocalDataSource.getBreedById(id)
        }
        return null
    }
}