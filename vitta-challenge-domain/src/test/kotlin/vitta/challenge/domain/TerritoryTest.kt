package vitta.challenge.domain

import org.junit.Assert
import org.junit.Test


class TerritoryTest {
    @Test
    fun create() {
        val territory = Territory(territoryId = TerritoryId(),
                                  name = Name("FirstTest"),
                                  start = Point(0, 0),
                                  end = Point(4, 4)
        )
        Assert.assertEquals(16, territory.area.value)
        Assert.assertEquals(0, territory.paintedArea.value)
        Assert.assertEquals(Status.ACTIVE, territory.status)
    }

    @Test
    fun delete() {
        val territory = Territory(territoryId = TerritoryId(),
                                  name = Name("FirstTest"),
                                  start = Point(0, 0),
                                  end = Point(4, 4)
        )
        territory.delete()
        Assert.assertEquals(16, territory.area.value)
        Assert.assertEquals(0, territory.paintedArea.value)
        Assert.assertEquals(Status.DELETED, territory.status)
    }

    @Test
    fun paint() {
        val territory = Territory(territoryId = TerritoryId(),
                                  name = Name("FirstTest"),
                                  start = Point(0, 0),
                                  end = Point(4, 4)
        )
        territory.paint(Point(0, 0))
        Assert.assertEquals(16, territory.area.value)
        Assert.assertEquals(1, territory.paintedArea.value)
        Assert.assertEquals(Status.ACTIVE, territory.status)
    }
}