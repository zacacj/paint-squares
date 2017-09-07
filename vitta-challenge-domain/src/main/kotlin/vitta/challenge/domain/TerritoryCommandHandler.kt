package vitta.challenge.domain

import br.com.zup.eventsourcing.core.Repository


class TerritoryCommandHandler(val repository: Repository<Territory>) {

    fun handle(command: CreateTerritory): Territory {
        val territory = Territory(territoryId = command.id,
                                  name = command.name,
                                  start = command.start,
                                  end = command.end
        )
        repository.save(territory)
        return territory
    }

/*    fun handle(command: DeleteTerritory) {
        val territory = repositoryManager.get(command.id)
        territory.delete()
        repositoryManager.save(territory)
    }

    fun handle(command: PaintSquare): Future<Square> {
        val territory = repositoryManager.get(command.id)
        territory.paint(command.point)
        return
    }*/
}