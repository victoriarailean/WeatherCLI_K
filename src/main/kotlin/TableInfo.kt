import models.ApiResponse
import models.Forecastday
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId

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
            c.forecast?.let { forecast ->
                val forecastday = getTomorrowForecast(forecast.forecastday)
                forecastday?.let { fd ->

                    System.out.printf(
                        "│ %-13s │ %-11s │ %-11s │ %-10s │ %-12s │ %-14s │ %-12s | %n",
                        c.location.name,
                        fd.day.maxtemp_c,
                        fd.day.mintemp_c,
                        fd.day.avghumidity,
                        fd.day.maxwind_kph,
                        "SSW",
                        dateFormat.format(fd.date)
                    );

                }
                println("─────────────────────────────────────────────────────────────────────────────────────────────────────────")
            }
        }
    }

    private fun getTomorrowForecast(forecastList: List<Forecastday>): Forecastday? {
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)

        return forecastList.find {
            it.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() == tomorrow
        }
    }
}


