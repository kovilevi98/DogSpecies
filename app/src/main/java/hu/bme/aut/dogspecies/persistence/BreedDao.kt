package hu.bme.aut.dogspecies.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.dogspecies.model.Breed

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed WHERE id = :id")
    fun getBreed(id: kotlin.Int): List<Breed>

    @Query("SELECT * FROM breed")
    fun getAllBreeds(): List<Breed>

    @Insert
    fun insertBreed(vararg grades: Breed)

    @Delete
    fun deleteBreed(grade: Breed)
}