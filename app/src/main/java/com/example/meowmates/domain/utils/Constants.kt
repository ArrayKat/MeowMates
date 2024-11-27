package com.example.meowmates.domain.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.websocket.WebSockets
import jakarta.inject.Singleton
import kotlin.time.Duration.Companion.seconds

//объект для доступа к БД Supabase

@InstallIn(SingletonComponent::class)
@Module
object Constants{
    val supabase = createSupabaseClient(
        supabaseUrl = "https://jmyjaavkcodgurgtwrwn.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpteWphYXZrY29kZ3VyZ3R3cnduIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjg1ODQ2OTIsImV4cCI6MjA0NDE2MDY5Mn0.y8cBwQ8JRcPMUb276IiIEDHxflWIAu-7ndEbs0lq3oI"
    ){
        //установка необходимых зависисмостей
        install(Auth)
        install(Postgrest)
        install(Realtime)
        install(Storage)

    }
//    @Provides
//    @Singleton
//    fun provideSupabaseClient(): SupabaseClient {
//        return createSupabaseClient(
//            supabaseUrl = "https://jmyjaavkcodgurgtwrwn.supabase.co",
//            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpteWphYXZrY29kZ3VyZ3R3cnduIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjg1ODQ2OTIsImV4cCI6MjA0NDE2MDY5Mn0.y8cBwQ8JRcPMUb276IiIEDHxflWIAu-7ndEbs0lq3oI"
//        ) {
//            install(Postgrest)
//            install(Auth)
//            install(Storage)
//            install(Realtime)
//        }
//    }

}