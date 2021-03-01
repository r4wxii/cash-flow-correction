package com.r4wxii.cashflowcorrection.data.db.di

import android.content.Context
import androidx.room.Room
import com.r4wxii.cashflowcorrection.data.db.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DbModule {
    @Binds
    @Singleton
    abstract fun provideAccountDatabase(impl: RoomDatabaseImpl): AccountDatabase

    @Module
    @InstallIn(SingletonComponent::class)
    internal object RoomDatabaseModule {
        @Provides
        @Singleton
        fun provideRoomDatabase(
            @ApplicationContext context: Context
        ): Database {
            return Room.databaseBuilder(
                context,
                Database::class.java,
                "in-app-db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @Singleton
        fun provideAccountDao(database: Database): AccountDao {
            return database.accountDao()
        }
    }
}