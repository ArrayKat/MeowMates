package com.example.meowmates.domain.messages

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MessagesRepositoryModule {

    @Provides
    fun provideMessagesRepository(postgrest: Postgrest):MessagesRepository{
        return MessagesRepositoryImpl(postgrest)
    }
}