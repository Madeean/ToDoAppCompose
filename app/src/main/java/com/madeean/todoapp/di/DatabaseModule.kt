package com.madeean.todoapp.di

import android.content.Context
import androidx.room.Room
import com.madeean.todoapp.data.TodoDatabase
import com.madeean.todoapp.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object DatabaseModule {

  @Singleton
  @Provides
  fun provideDatabase(
    @ApplicationContext context: Context
  ) = Room.databaseBuilder(context, TodoDatabase::class.java, DATABASE_NAME).build()

  @Singleton
  @Provides
  fun provideDao(database: TodoDatabase) = database.todoDao()
}