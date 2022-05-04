package hu.bme.aut.dogspecies.repository

import hu.bme.aut.dogspecies.datasource.database.FavoriteDao
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.model.toBreed
import hu.bme.aut.dogspecies.model.toBreedEntity
import hu.bme.aut.dogspecies.model.toFavoriteEntity
import javax.inject.Inject

class FavoriteLocalDataSource  @Inject constructor(
    private val favoriteDao: FavoriteDao
) {

    fun getBreeds() = favoriteDao.getFavorites().map { it.toBreed() }

    fun deleteBreed(breed: Breed) = favoriteDao.deleteFavorite((breed.toFavoriteEntity()))

    fun insertBreed(breed: Breed) = favoriteDao.insertFavorite(breed.toFavoriteEntity())

}