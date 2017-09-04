package vitta.challenge.query.repository

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import vitta.challenge.domain.Area
import vitta.challenge.domain.Id
import vitta.challenge.domain.Name
import vitta.challenge.domain.Painted
import vitta.challenge.domain.Point
import vitta.challenge.domain.Square
import vitta.challenge.domain.Territory
import vitta.challenge.query.repository.repositories.SquareRepository
import vitta.challenge.query.repository.repositories.TerritoryRepository

@RunWith(SpringRunner::class)
@SpringBootTest
class VittaChallengeQueryRepositoryApplicationTests {

	@Autowired
	lateinit var territoryRepository: TerritoryRepository

	@Autowired
	lateinit var squareRepository: SquareRepository

	@Test
	fun findTerritoriesById(){
		val territory = Territory(id = Id("first"),
                                  name = Name("First Name"),
                                  area = Area(value = 50),
                                  start = Point(x = 1, y = 1),
                                  end = Point(x = 40, y = 40),
                                  paitedArea = Area(value = 20)
        )
		territoryRepository.save(territory).then().block()
		val territoryFetched = territoryRepository.findOneById(Id("first")).block()
		Assert.assertEquals(territory.name, territoryFetched!!.name)
	}

	@Test
	fun findSquareByPoint(){
		val square = Square(Point(x = 1, y = 2), Painted(false))
		squareRepository.save(square).then().block()
        val squareFetched = squareRepository.findOneByPoint(Point(1,2)).block()
        Assert.assertEquals(square.paitend, squareFetched!!.paitend)
	}
}
