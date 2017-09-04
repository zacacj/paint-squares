package vitta.challenge.query.repository

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import vitta.challenge.domain.Area
import vitta.challenge.domain.Id
import vitta.challenge.domain.Name
import vitta.challenge.domain.Painted
import vitta.challenge.domain.Point
import vitta.challenge.domain.Square
import vitta.challenge.domain.Territory
import vitta.challenge.query.repository.config.MongoConfig
import vitta.challenge.query.repository.repositories.SquareRepository
import vitta.challenge.query.repository.repositories.TerritoryRepository

@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(classes = arrayOf(MongoConfig::class))
class VittaChallengeQueryRepositoryApplicationTests {

	@Autowired
	lateinit var territoryRepository: TerritoryRepository

	@Autowired
	lateinit var squareRepository: SquareRepository

	@Test
	fun findTerritoriesById(){
		val territory = Territory(id = Id("first"),
                                  name = Name("First Name"),
                                  start = Point(x = 0, y = 0),
                                  end = Point(x = 40, y = 40)
        )
		territoryRepository.save(territory).then().block()
		val territoryFetched = territoryRepository.findOneById(Id("first")).block()
		Assert.assertEquals(territory.name, territoryFetched!!.name)
		Assert.assertEquals(Area(1600), territoryFetched.area)
		Assert.assertEquals(Area(0),territoryFetched.paintedArea)
	}

	@Test
	fun findSquareByPoint(){
		val square = Square(Point(x = 2, y = 4), Painted(true))
		squareRepository.save(square).then().block()
        val squareFetched = squareRepository.findOneByPoint(Point(2,4)).block()
        Assert.assertEquals(Painted(true), squareFetched!!.painted)
	}
}
