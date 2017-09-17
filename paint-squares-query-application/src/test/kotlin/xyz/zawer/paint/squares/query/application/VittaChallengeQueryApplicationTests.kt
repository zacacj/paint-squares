package xyz.zawer.paint.squares.query.application

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import xyz.zawer.paint.squares.domain.Painted
import xyz.zawer.paint.squares.domain.Point
import xyz.zawer.paint.squares.domain.Square
import xyz.zawer.paint.squares.query.repository.SquareRepository
import xyz.zawer.paint.squares.query.repository.TerritoryRepository
import xyz.zawer.paint.squares.representation.PointRepresentation
import xyz.zawer.paint.squares.representation.TerritoryRepresentation

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PaintSquaresQueryApplicationTests {

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

