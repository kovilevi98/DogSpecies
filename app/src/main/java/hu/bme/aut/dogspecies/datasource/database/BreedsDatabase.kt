package hu.bme.aut.dogspecies.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.dogspecies.model.BreedEntity

@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BreedsDatabase : RoomDatabase() {
    abstract fun breedsDao(): BreedsDao
}