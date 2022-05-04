package hu.bme.aut.dogspecies.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.dogspecies.model.BreedEntity
import hu.bme.aut.dogspecies.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}