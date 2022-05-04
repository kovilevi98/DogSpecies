package hu.bme.aut.dogspecies.repository

import hu.bme.aut.dogspecies.model.Breed
import javax.inject.Inject

class FavoriteRepository @Inject
constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource,
) {

    fun getFavorites(): List<Breed> {
            return favoriteLocalDataSource.getBreeds()
    }

    fun insertFavorite(breed: Breed){
        return favoriteLocalDataSource.insertBreed(breed)
    }

    fun deleteFavorite(breed: Breed){
        return favoriteLocalDataSource.deleteBreed(breed)
    }

}