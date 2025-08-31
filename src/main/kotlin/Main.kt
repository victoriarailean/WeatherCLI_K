import models.ApiResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.ForecastService
import java.util.*

fun main() {
    var httpClient = OkHttpClient.Builder()
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build();

    var apiKey : String = System.getenv("api_key");
    var cities : List<String> = listOf("Chisinau", "Madrid", "Kyiv", "Amsterdam");
    var apiResponseList = ArrayList<ApiResponse>()

    if(apiKey == null){
        throw  Exception("api_key environment variable not provided!");
    }
    val forecastService: ForecastService = retrofit.create(ForecastService::class.java)

    for( city in cities){
        val apiResponseCall: Call<ApiResponse?>? = forecastService.getApiResponse(city, apiKey, 2)

        try {
            val responseResponse: Response<ApiResponse?> = apiResponseCall?.execute()
                ?: throw Exception("Api call is null")
            val apiResponse: ApiResponse? = responseResponse.body()
            if (apiResponse != null) {
                apiResponseList.add(apiResponse)

                Thread.sleep(200);
            }

            Thread.sleep(200)
        } catch (e: Exception) {
            throw e
        }

    }
    var tableInfo  =  TableInfo();
    tableInfo.printTable(apiResponseList);
}



