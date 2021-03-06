package hu.bme.aut.dogspecies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.dogspecies.datasource.database.FavoriteDao
import hu.bme.aut.dogspecies.datasource.database.FavoriteDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModuleFavorite {
    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,
        "favorites"
    ).build()

    @Provides
    @Singleton
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }

}