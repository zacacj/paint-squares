package xyz.zawer.paint.squares.ui.integration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import feign.Feign
import feign.Request
import feign.Retryer
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.zawer.paint.squares.ui.integration.api.TerritoryApi


@Configuration
class FeignConfig {

    private val ONE_SECOND = 1000
    private val TEN_SECONDS = ONE_SECOND * 10
    private val ONE_MINUTE = 60000

    @Value("\${paint.squares.query.url}")
    private lateinit var paintSquaresQueryUrl: String


    @Bean
    fun catalogManagerClientFeign(): TerritoryApi {

        return Feign.builder()
                .encoder(JacksonEncoder(JacksonExtension.jacksonObjectMapper))
                .decoder(JacksonDecoder(JacksonExtension.jacksonObjectMapper))
                .client(OkHttpClient())
                .options(Request.Options(TEN_SECONDS, ONE_MINUTE))
                .retryer(Retryer.NEVER_RETRY)
                .target(TerritoryApi::class.java, paintSquaresQueryUrl)
    }
}

object JacksonExtension {

    val jacksonObjectMapper: ObjectMapper by lazy {
        ObjectMapper().registerModule(JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(KotlinModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

}