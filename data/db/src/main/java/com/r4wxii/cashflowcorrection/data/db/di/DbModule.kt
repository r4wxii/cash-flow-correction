package com.r4wxii.cashflowcorrection.data.db.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.r4wxii.cashflowcorrection.data.db.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
abstract class DbModule {
    @Binds
    abstract fun provideAccountDatabase(impl: RoomDatabaseImpl): AccountDatabase

    companion object {
        @Singleton
        @Provides
        fun provideRoomDatabase(
            context: Context
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
        fun provideAccountDao(database: Database): AccountDao {
            return database.accountDao()
        }
    }
}