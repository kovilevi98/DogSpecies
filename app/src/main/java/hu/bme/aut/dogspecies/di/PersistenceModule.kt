package hu.bme.aut.dogspecies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.dogspecies.datasource.database.BreedsDao
import hu.bme.aut.dogspecies.datasource.database.BreedsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BreedsDatabase = Room.databaseBuilder(
        context,
        BreedsDatabase::class.java,
        "breeds"
    ).build()

    @Provides
    @Singleton
    fun provideBreedDao(breedsDatabase: BreedsDatabase): BreedsDao {
        return breedsDatabase.breedsDao()
    }

}