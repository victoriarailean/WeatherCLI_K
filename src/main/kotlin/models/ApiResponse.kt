package models


data class ApiResponse(
    var forecast: Forecast,
    var location: Location
)