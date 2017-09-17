package xyz.zawer.paint.squares.command.repository

import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.springframework.stereotype.Service
import xyz.zawer.paint.squares.domain.ErrorAggregateRoot
import xyz.zawer.paint.squares.domain.Plane
import xyz.zawer.paint.squares.domain.Territory

@Service
class CommandTerritoryRepository : EventStoreRepository<Territory>()

@Service
class CommandPlaneRepository : EventStoreRepository<Plane>()

@Service
class CommandErrorRepository : EventStoreRepository<ErrorAggregateRoot>()