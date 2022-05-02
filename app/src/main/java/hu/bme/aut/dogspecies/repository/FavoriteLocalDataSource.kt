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

    fun getBreedById(id: Int) = favoriteDao.getFavorite(id).toBreed()

    fun insertBreed(breed: Breed) = favoriteDao.insertFavorite(breed.toFavoriteEntity())

    fun insertFavoriteBreed(breed: Breed) = favoriteDao.insertFavorite(breed.toFavoriteEntity())
}