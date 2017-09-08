package vitta.challenge.domain

import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Repository


class TerritoryCommandHandler(val territoryRepository: Repository<Territory>,
                              val planeRepository: Repository<Plane>) {

    init {
        try {
            planeRepository.save(Plane())
        } catch (e: Exception){
            println("Plane already initialized")
        }
    }
    fun handle(command: CreateTerritory): Territory {

        val plane = planeRepository.get(AggregateId("total"))
        for (x in command.start.x .. command.end.x)
            for (y in command.start.y.. command.end.y)
                plane.addSquare(AddSquare(Square(territoryId = command.id, point = Point(x,y),painted = Painted
                (false))))

        planeRepository.save(plane)

        val territory = Territory(territoryId = command.id,
                                  name = command.name,
                                  start = command.start,
                                  end = command.end
        )
        territoryRepository.save(territory)
        return territory
    }

    fun handle(command: DeleteTerritory) {
        val territory = territoryRepository.get(command.id)

        val plane = planeRepository.get(AggregateId("total"))
        for (x in territory.start!!.x .. territory.end!!.x)
            for (y in territory.start!!.y.. territory.end!!.y)
                plane.deleteSquare(DeleteSquare(Square(territoryId = command.id,point = Point(x,y),painted = Painted
                (false))))
        planeRepository.save(plane)
        territory.delete()
        territoryRepository.save(territory)
    }

    fun handle(command: PaintSquare): Square{

        val plane = planeRepository.get(AggregateId("total"))

        val territoryId = plane.area[command.point.x][command.point.y]?.territoryId ?: throw Repository.NotFoundException()

        val territory = territoryRepository.get(territoryId)

        territory.paint(command.point)
        territoryRepository.save(territory)
        return Square(territoryId,command.point,Painted(true))
    }
}