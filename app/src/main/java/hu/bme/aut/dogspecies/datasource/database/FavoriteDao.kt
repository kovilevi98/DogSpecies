package hu.bme.aut.dogspecies.datasource.database

import androidx.room.*
import hu.bme.aut.dogspecies.model.BreedEntity
import hu.bme.aut.dogspecies.model.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavorite(id: kotlin.Int): FavoriteEntity

    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(vararg breeds: FavoriteEntity)

    @Delete
    fun deleteFavorite(grade: FavoriteEntity)
}