package com.example.astronomicalphotooftheday.domain.use_case

data class ApodUseCases(
    val getTodayApod: GetTodayApod,
    val getRandomApods: GetRandomApods,
)
