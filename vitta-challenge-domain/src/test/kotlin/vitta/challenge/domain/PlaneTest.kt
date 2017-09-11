package vitta.challenge.domain

import org.junit.Assert
import org.junit.Test

class PlaneTest {
    @Test
    fun addSquare() {
        val plane = Plane()
        val square = Square(territoryId = TerritoryId(),
                            point = Point(0, 0),
                            painted = Painted(true)
        )
        plane.addSquare(AddSquare(square))

        Assert.assertEquals(square, plane.area[0][0])
    }

    @Test
    fun deleteSquare() {
        val plane = Plane()
        val square = Square(territoryId = TerritoryId(),
                            point = Point(0, 0),
                            painted = Painted(true)
        )
        plane.addSquare(AddSquare(square))

        Assert.assertEquals(square, plane.area[0][0])

        plane.deleteSquare(DeleteSquare(square))

        Assert.assertEquals(null, plane.area[0][0])
    }

}