import models.ApiResponse
import models.Forecastday
import java.text.SimpleDateFormat

class TableInfo {

    fun printTable(apiResponseList: List<ApiResponse>) {

        println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf(
            "│ %-13s │ %-11s │ %-11s │ %-10s │ %-12s │ %-14s │ %-12s | %n",
            " ",
            "Max Temp.",
            "Min Temp.",
            "Humidity",
            "Wind Speed",
            "Wind Direction",
            "Date"
        );
        println("─────────────────────────────────────────────────────────────────────────────────────────────────────────");

        var dateFormat = SimpleDateFormat("yyyy-MM-dd");

        for (c in apiResponseList) {
            if (c.forecast != null && c.forecast.forecastday.size > 1) {
                var forecastday: Forecastday = c.forecast.forecastday.get(1);

                System.out.printf(
                    "│ %-13s │ %-11s │ %-11s │ %-10s │ %-12s │ %-14s │ %-12s | %n",
                    c.location.name,
                    forecastday.day.maxtemp_c,
                    forecastday.day.mintemp_c,
                    forecastday.day.avghumidity,
                    forecastday.day.maxwind_kph,
                    "SSW",
                    dateFormat.format(forecastday.date)
                );

            }
            println("─────────────────────────────────────────────────────────────────────────────────────────────────────────")
        }
    }
}


