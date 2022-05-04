package hu.bme.aut.dogspecies.repository

import hu.bme.aut.dogspecies.datasource.database.BreedsDao
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.model.toBreed
import hu.bme.aut.dogspecies.model.toBreedEntity
import javax.inject.Inject

class BreedLocalDataSource  @Inject constructor(
    private val breedDao: BreedsDao
) {

    fun getBreeds() = breedDao.getAllBreeds().map { it.toBreed() }

    fun getBreedById(id: Int) = breedDao.getBreed(id).toBreed()

    fun insertBreed(breed: Breed) = breedDao.insertBreed(breed.toBreedEntity())

    fun insertFavoriteBreed(breed: Breed) = breedDao.insertBreed(breed.toBreedEntity())
}