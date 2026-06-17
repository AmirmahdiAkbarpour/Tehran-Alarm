package com.tehranalaarm.app.di

import android.content.Context
import android.content.SharedPreferences
import com.tehranalaarm.app.data.local.RatingManager
import com.tehranalaarm.app.data.repository.AlertRepositoryImpl
import com.tehranalaarm.app.domain.repository.AlertRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAlertRepository(
        alertRepositoryImpl: AlertRepositoryImpl
    ): AlertRepository = alertRepositoryImpl
}

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("tehran_alarm", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideRatingManager(
        preferences: SharedPreferences
    ): RatingManager {
        return RatingManager(preferences)
    }
}
