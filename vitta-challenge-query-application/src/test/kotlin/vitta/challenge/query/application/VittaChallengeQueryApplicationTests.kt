package vitta.challenge.query.application

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import vitta.challenge.domain.Painted
import vitta.challenge.domain.Point
import vitta.challenge.domain.Square
import vitta.challenge.query.repository.SquareRepository
import vitta.challenge.query.repository.TerritoryRepository
import vitta.challenge.representation.PointRepresentation
import vitta.challenge.representation.TerritoryRepresentation

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class VittaChallengeQueryApplicationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var territoryRepository: TerritoryRepository

    @Autowired
    lateinit var squareRepository: SquareRepository

    @Before
    fun setup() {

        val territory1 = TerritoryRepresentation(id = "first",
                                                 name = "First Name",
                                                 start = PointRepresentation(x = 1, y = 1),
                                                 end = PointRepresentation(x = 40, y = 40)
        )
        val territory2 = TerritoryRepresentation(id = "second",
                                                 name = "Second Name",
                                                 start = PointRepresentation(x = 1, y = 1),
                                                 end = PointRepresentation(x = 40, y = 40)
        )

        territoryRepository.saveAll(listOf(territory1, territory2)).then().block()

        val square = Square(null, Point(x = 1, y = 2), Painted(false))
        squareRepository.save(square).then().block()
    }

    @Test
    fun getListOfTerritories() {
        webTestClient.get().uri("/territories").exchange().expectStatus()
                .is2xxSuccessful
    }

    @Test
    fun getTerritoryById() {
        webTestClient.get().uri("/territories/first").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
                .is2xxSuccessful
    }

    @Test
    fun getTerritoryById_NotFound() {
        webTestClient.get().uri("/territories/third").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
                .isNotFound
    }

    @Test
    fun getSquares() {
        webTestClient.get().uri("/squares/1/2").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
                .is2xxSuccessful
    }

    @Test
    fun getSquares_NotFound() {
        webTestClient.get().uri("/squares/4/5").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
                .isNotFound
    }

}

