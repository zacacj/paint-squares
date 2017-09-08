package vitta.challenge.command.application

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.RouterFunction
import vitta.challenge.command.application.config.ControllerApplicationConfig
import vitta.challenge.command.application.representation.PointRepresentation
import vitta.challenge.command.application.representation.TerritoryRepresentation


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(ControllerApplicationConfig::class))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CommandTerritoryTests {

    @Autowired
    lateinit var routeFunction : RouterFunction<*>

    lateinit var webTestClient : WebTestClient

    @Before
    fun setup(){
        webTestClient = WebTestClient.bindToRouterFunction(routeFunction)
                .configureClient()
                .build()
    }

    @Test
    fun addTerritory() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation(x = 5, y = 5)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .isCreated
    }

    @Test
    fun addTerritory_OverlappingArea() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x =7, y = 7),
                end = PointRepresentation(x = 8, y = 8)
        )

        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .isCreated

        val territory2 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x =5, y = 5),
                end = PointRepresentation(x = 15, y = 15)
        )

        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory2))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_NotNameInformed() {

        val territory1 = TerritoryRepresentation(
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation(x = 40, y = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_NameInvalid() {

        val territory1 = TerritoryRepresentation(
                name = "",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation(x = 40, y = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_StartNotInformed() {

        val territory1 = TerritoryRepresentation(
                name = "Teste",
                end = PointRepresentation(x = 40, y = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_EndNotInformed() {

        val territory1 = TerritoryRepresentation(
                name = "Teste",
                start = PointRepresentation(x = 40, y = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_StartXNotInformed() {

        val territory1 = TerritoryRepresentation(
                name = "",
                start = PointRepresentation( y = 40),
                end = PointRepresentation(x = 5, y = 5)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_StartYNotInformed() {

        val territory1 = TerritoryRepresentation(
                name = "Valid",
                start = PointRepresentation( x = 40),
                end = PointRepresentation(x = 5, y = 5)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }


    @Test
    fun addTerritory_EndXNotInformed() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation( y = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_EndYNotInformed() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation( x = 40)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_EndXEqualStartX() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation(x = 1, y = 5)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_EndYEqualStartY() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = 1),
                end = PointRepresentation(x = 5, y = 1)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_XMustBeGreaterOrEqualThenZero() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = -1, y = 4),
                end = PointRepresentation(x = 5, y = 1)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }

    @Test
    fun addTerritory_YMustBeGreaterOrEqualThenZero() {

        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 1, y = -1),
                end = PointRepresentation(x = 5, y = 1)
        )
        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .is4xxClientError
    }


    @Test
    fun deleteTerritory(){
        val territory5 = TerritoryRepresentation(
                name = "First Named",
                start = PointRepresentation(x = 33, y = 33),
                end = PointRepresentation(x = 38, y = 38)
        )
       val id = webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory5))
                .exchange()
                .expectStatus()
                .isCreated
                .expectBody(TerritoryRepresentation::class.java).returnResult().responseBody.id

        webTestClient.delete().uri("/territories/$id").exchange().expectStatus().is2xxSuccessful
    }

    @Test
    fun deleteTerritory_NotFound(){
        webTestClient.delete().uri("/territories/notfound").exchange().expectStatus().isNotFound
    }


    @Test
    fun deleteTerritory_AndAddAgain(){
        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 47, y = 47),
                end = PointRepresentation(x = 49, y = 49)
        )
        val id = webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .isCreated
                .expectBody(TerritoryRepresentation::class.java).returnResult().responseBody.id

        webTestClient.delete().uri("/territories/$id")
                .exchange()
                .expectStatus()
                .is2xxSuccessful

        webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .isCreated
    }


    @Test
    fun paintTerritory(){
        val territory1 = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 56, y = 56),
                end = PointRepresentation(x = 60, y = 60)
        )
        val id = webTestClient.post().uri("/territories")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(territory1))
                .exchange()
                .expectStatus()
                .isCreated
                .expectBody(TerritoryRepresentation::class.java).returnResult().responseBody.id

        webTestClient.patch().uri("/squares/56/56/")
                .exchange()
                .expectStatus()
                .is2xxSuccessful

    }

    @Test
    fun paintTerritory_NotFound(){
        webTestClient.patch().uri("/squares/111/11/")
                .exchange()
                .expectStatus()
                .isNotFound

    }

}

