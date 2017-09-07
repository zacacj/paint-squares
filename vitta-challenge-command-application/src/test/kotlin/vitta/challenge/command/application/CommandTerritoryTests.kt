package vitta.challenge.command.application

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import vitta.challenge.command.application.config.ControllerApplicationConfig
import vitta.challenge.command.application.representation.PointRepresentation
import vitta.challenge.command.application.representation.TerritoryRepresentation

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(ControllerApplicationConfig::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CommandTerritoryTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun addTerritory() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation(x = 40, y = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .isCreated
    }


}

