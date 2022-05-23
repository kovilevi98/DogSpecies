package hu.bme.aut.dogspecies

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.dogspecies.datasource.database.BreedsDao
import hu.bme.aut.dogspecies.datasource.database.BreedsDatabase
import hu.bme.aut.dogspecies.di.PersistenceModule
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PersistenceModule::class]
)
object FakePersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BreedsDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            BreedsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideBreedDao(breedDatabase: BreedsDatabase): BreedsDao {
        return breedDatabase.breedsDao()
    }

}