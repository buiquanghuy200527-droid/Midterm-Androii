package com.example.midterm_ai.data.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather?
)

data class CurrentWeather(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("windspeed")
    val windspeed: Double,
    @SerializedName("weathercode")
    val weathercode: Int
)

fun getWeatherConditionText(code: Int): String {
    return when (code) {
        0 -> "Trời quang mây (Clear sky)"
        1, 2, 3 -> "Ít mây / Nắng nhẹ (Partly cloudy)"
        45, 48 -> "Có sương mù (Foggy)"
        51, 53, 55 -> "Mưa nhỏ (Light drizzle)"
        61, 63, 65 -> "Mưa rào (Rainy)"
        80, 81, 82 -> "Mưa dông (Rain showers)"
        95, 96, 99 -> "Có dông sét (Thunderstorm)"
        else -> "Thời tiết bình thường"
    }
}
