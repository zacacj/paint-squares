package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Repository
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert
import org.junit.Test

class TerritoryCommandHandlerTest {

    val plane = run {
        val futureplane = Plane()
        futureplane.addSquare(AddSquare(Square(TerritoryId("TerritoryId"), Point(5, 5), Painted(true))))
        futureplane.addSquare(AddSquare(Square(TerritoryId("deleteName"), Point(7, 7), Painted(true))))
        futureplane.addSquare(AddSquare(Square(TerritoryId("deleteName"), Point(7, 8), Painted(true))))
        futureplane.addSquare(AddSquare(Square(TerritoryId("deleteName"), Point(8, 7), Painted(true))))
        futureplane.addSquare(AddSquare(Square(TerritoryId("deleteName"), Point(8, 8), Painted(true))))
        futureplane.addSquare(AddSquare(Square(TerritoryId("notPainted"), Point(10, 10), Painted(false))))
        futureplane
    }
    val deleteTerritoryId = TerritoryId("delete")
    val territoryRepository = mock<Repository<Territory>>() {
        on { get(deleteTerritoryId) }.doReturn(Territory(territoryId = TerritoryId("delete"),
                                                         name = Name("deleteName"),
                                                         start = Point(7, 7),
                                                         end = Point(8, 8)
        )
        )
        on { get(TerritoryId("deleteError")) }.doReturn(Territory(territoryId = TerritoryId("deleteError"),
                                                                  name = Name("deleteError"),
                                                                  start = Point(9, 9),
                                                                  end = Point(10, 10)
        )
        )
        on { get(TerritoryId("notPainted")) }.doReturn(Territory(territoryId = TerritoryId("notPainted"),
                                                                 name = Name("notPainted"),
                                                                 start = Point(10, 10),
                                                                 end = Point(11, 11)
        )
        )
    }
    val planeRepository = mock<Repository<Plane>>() {
        on { get(AggregateId("total")) }.doReturn(plane)
    }
    val territoryCommandHandler = TerritoryCommandHandler(territoryRepository = territoryRepository,
                                                          planeRepository = planeRepository
    )

    @Test
    fun handleCreateTerritorySuccess() {
        val command = CreateTerritory(id = TerritoryId(),
                                      name = Name("test"),
                                      start = Point(0, 0),
                                      end = Point(4, 4)
        )

        val territory = territoryCommandHandler.handle(command)

        Assert.assertEquals(16, territory.area.value)
        Assert.assertEquals(0, territory.paintedArea.value)
        Assert.assertEquals(Status.ACTIVE, territory.status)
    }

    @Test(expected = Plane.SquareOverlapping::class)
    fun handleCreateTerritoryFail() {
        val command = CreateTerritory(id = TerritoryId(),
                                      name = Name("test"),
                                      start = Point(5, 5),
                                      end = Point(6, 6)
        )

        territoryCommandHandler.handle(command)
    }

    @Test
    fun handleDeleteSuccess() {
        val command = DeleteTerritory(deleteTerritoryId)
        territoryCommandHandler.handle(command)
    }

    @Test(expected = Plane.InconsistentSquare::class)
    fun handleDeleteFail() {
        val command = DeleteTerritory(TerritoryId("deleteError"))
        territoryCommandHandler.handle(command)
    }

    @Test
    fun handlePaintSuccess() {
        val command = PaintSquare(Point(10, 10))
        val square = territoryCommandHandler.handle(command)
        Assert.assertTrue(square.painted.value)
    }

    @Test(expected = Repository.NotFoundException::class)
    fun handlePaintFail() {
        val command = PaintSquare(Point(15, 15))
        val square = territoryCommandHandler.handle(command)
        Assert.assertTrue(square.painted.value)
    }

}