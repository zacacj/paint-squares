package vitta.challenge.command.repository

import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.springframework.stereotype.Service
import vitta.challenge.domain.ErrorAggregateRoot
import vitta.challenge.domain.Plane
import vitta.challenge.domain.Territory

@Service
class CommandTerritoryRepository : EventStoreRepository<Territory>()

@Service
class CommandPlaneRepository : EventStoreRepository<Plane>()

@Service
class CommandErrorRepository : EventStoreRepository<ErrorAggregateRoot>()