package hu.bme.aut.dogspecies.datasource.database

import androidx.room.*
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.model.BreedEntity

@Dao
interface BreedsDao {

    @Query("SELECT * FROM breeds WHERE id = :id")
    fun getBreed(id: kotlin.Int): BreedEntity

    @Query("SELECT * FROM breeds")
    fun getAllBreeds(): List<BreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreed(vararg breeds: BreedEntity)

    @Delete
    fun deleteBreed(grade: BreedEntity)
}