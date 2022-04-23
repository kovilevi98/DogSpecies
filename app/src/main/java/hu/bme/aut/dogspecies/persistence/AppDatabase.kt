package hu.bme.aut.dogspecies.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.dogspecies.model.Breed

@Database(entities = [Breed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gradeDao(): BreedDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "grade_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}