package hu.vanio.kotlin.hazifeladat.ms

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@SpringBootApplication
class WeatherApp : CommandLineRunner {
    override fun run(vararg args: String?) {
        val restClient = RestClient.builder().baseUrl("https://api.open-meteo.com").build()
        val adapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(adapter).build()

        val service: ForecastService = factory.createClient(ForecastService::class.java)
        val forecastDto = service.getForecast(47.4984, 19.0404)
        calculateAverageTemperature(forecastDto)
    }

    fun calculateAverageTemperature(forecastDto: ForecastDto) =
        forecastDto.hourly.time
            .mapIndexed { index, key -> key to forecastDto.hourly.temperature2m[index] }
            .groupBy({ it.first.toLocalDate() }, { it.second })
            .map { it.key to it.value.average() }
}

fun main() {
    runApplication<WeatherApp>()
}