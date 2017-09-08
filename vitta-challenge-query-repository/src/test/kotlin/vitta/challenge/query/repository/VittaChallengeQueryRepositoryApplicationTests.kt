package vitta.challenge.query.repository

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import vitta.challenge.domain.Area
import vitta.challenge.domain.Name
import vitta.challenge.domain.Painted
import vitta.challenge.domain.Point
import vitta.challenge.domain.Square
import vitta.challenge.domain.Territory
import vitta.challenge.domain.TerritoryId

@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(classes = arrayOf(MongoConfig::class))
class VittaChallengeQueryRepositoryApplicationTests {

    @Autowired
    lateinit var territoryRepository: TerritoryRepository

    @Autowired
    lateinit var squareRepository: SquareRepository

    @Test
    fun findTerritoriesById() {
        val territoryId = TerritoryId()
        val territory = Territory(territoryId = territoryId,
                                  name = Name("First Name"),
                                  start = Point(x = 0, y = 0),
                                  end = Point(x = 40, y = 40)
        )
        territoryRepository.save(territory).then().block()
        val territoryFetched = territoryRepository.findById(territoryId).block()
        Assert.assertEquals(territory.name, territoryFetched!!.name)
        Assert.assertEquals(Area(1600), territoryFetched.area)
        Assert.assertEquals(Area(0), territoryFetched.paintedArea)
    }

    @Test
    fun findSquareByPoint() {
        val square = Square(null, Point(x = 2, y = 4), Painted(true))
        squareRepository.save(square).then().block()
        val squareFetched = squareRepository.findOneByPoint(Point(2,4)).block()
        Assert.assertEquals(Painted(true), squareFetched!!.painted)
    }
}

