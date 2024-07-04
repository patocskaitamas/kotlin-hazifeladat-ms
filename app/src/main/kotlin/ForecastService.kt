package hu.vanio.kotlin.hazifeladat.ms

import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange



interface ForecastService {

        @GetExchange("/v1/forecast", accept = arrayOf("application/json;charset=utf-8"))
        fun getForecast(@RequestParam latitude: Double,
                        @RequestParam longitude: Double,
                        @RequestParam hourly: String = "temperature_2m",
                        @RequestParam timezone: String = "auto"): ForecastDto

}