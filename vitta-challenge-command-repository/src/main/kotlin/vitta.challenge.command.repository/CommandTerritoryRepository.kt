package vitta.challenge.command.repository

import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.springframework.stereotype.Service
import vitta.challenge.domain.Territory

@Service
class CommandTerritoryRepository : EventStoreRepository<Territory>()