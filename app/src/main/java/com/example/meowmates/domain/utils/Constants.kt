package com.example.meowmates.domain.utils

import io.github.jan.supabase.auth.Auth

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


object Constants{
    val supabase = createSupabaseClient(
        supabaseUrl = "https://jmyjaavkcodgurgtwrwn.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpteWphYXZrY29kZ3VyZ3R3cnduIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjg1ODQ2OTIsImV4cCI6MjA0NDE2MDY5Mn0.y8cBwQ8JRcPMUb276IiIEDHxflWIAu-7ndEbs0lq3oI"
    ){
        install(Auth)
        install(Postgrest)
    }
}