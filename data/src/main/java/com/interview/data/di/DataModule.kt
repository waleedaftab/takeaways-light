package com.interview.data.di

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import com.google.gson.Gson
import com.interview.data.datasource.filesource.AssetFileSource
import com.interview.data.datasource.filesource.AssetFileSourceImpl
import com.interview.data.datasource.room.FavoriteRestaurantDao
import com.interview.data.datasource.room.FavoritesDatabase
import com.interview.data.mapper.JSONToRestaurantListMapper
import com.interview.data.mapper.Mapper
import com.interview.data.repository.FavoritesRepositoryImpl
import com.interview.data.repository.RestaurantRepositoryImpl
import com.interview.domain.model.Restaurant
import com.interview.domain.repositories.FavoritesRepository
import com.interview.domain.repositories.RestaurantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesGson() = Gson()

    @Singleton
    @Provides
    fun providesAssetManager(context: Context): AssetManager = context.assets

    @Singleton
    @Provides
    fun providesAssetFileSource(assetManager: AssetManager): AssetFileSource =
        AssetFileSourceImpl(assetManager)

    @Singleton
    @Provides
    fun providesJSONToRestaurantListMapper(gson: Gson): @JvmWildcard Mapper<String, List<Restaurant>> =
        JSONToRestaurantListMapper(gson)

    @Singleton
    @Provides
    fun providesRestaurantListRepository(
        assetFileSource: AssetFileSource,
        mapper: Mapper<String, List<Restaurant>>
    ): RestaurantRepository =
        RestaurantRepositoryImpl(assetFileSource, mapper)

    @Singleton
    @Provides
    fun providesFavoritesDatabase(context: Context): FavoritesDatabase {
        return Room.databaseBuilder(
            context,
            FavoritesDatabase::class.java, "favoritesdatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun providesFavoritesDao(db: FavoritesDatabase): FavoriteRestaurantDao {
        return db.favoriteRestaurantDao()
    }

    @Singleton
    @Provides
    fun providesFavoritesRepository(dao: FavoriteRestaurantDao): FavoritesRepository {
        return FavoritesRepositoryImpl(dao)
    }
}