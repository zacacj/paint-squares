package vitta.challenge.ui.integration

import feign.Feign
import feign.Request
import feign.Retryer
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.okhttp.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vitta.challenge.ui.integration.api.TerritoryApi


@Configuration
class FeignConfig {

    private val ONE_SECOND = 1000
    private val TEN_SECONDS = ONE_SECOND * 10
    private val ONE_MINUTE = 60000

    @Value("\${vitta.challenge.query.url}")
    private lateinit var vittaChallenteQueryUrl: String


    @Bean
    fun catalogManagerClientFeign(): TerritoryApi {

        return Feign.builder()
                .encoder(GsonEncoder())
                .decoder(GsonDecoder())
                .client(OkHttpClient())
                .options(Request.Options(TEN_SECONDS, ONE_MINUTE))
                .retryer(Retryer.NEVER_RETRY)
                .target(TerritoryApi::class.java, vittaChallenteQueryUrl)
    }
}