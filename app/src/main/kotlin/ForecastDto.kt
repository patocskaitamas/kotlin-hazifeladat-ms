package hu.vanio.kotlin.hazifeladat.ms

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ForecastDto(
    val latitude: Double,
    val longitude: Double,
    @JsonProperty("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    val timezone: String,
    @JsonProperty("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @JsonProperty("generationtime_ms")
    val generationtimeMs: Double,
    val elevation: Double,
    @JsonProperty("hourly_units")
    val hourlyUnits: Map<String, String>,
    val hourly: HourlyTemperature
)

data class HourlyTemperature(
    val time: Array<LocalDateTime>,
    @JsonProperty("temperature_2m")
    val temperature2m: Array<Double>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HourlyTemperature) return false

        if (!time.contentEquals(other.time)) return false
        if (!temperature2m.contentEquals(other.temperature2m)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = time.contentHashCode()
        result = 31 * result + temperature2m.contentHashCode()
        return result
    }
}
