package services

import models.ApiResponse
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ForecastService {
    @GET("/v1/forecast.json")
    fun getApiResponse(
        @Query("q") q: String?,
        @Query("key") key: String?,
        @Query("days") days: Int
    ): Call<ApiResponse?>?
}