package vitta.challenge.command.application.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import vitta.challenge.command.repository.CommandPlaneRepository
import vitta.challenge.command.repository.CommandTerritoryRepository
import vitta.challenge.domain.TerritoryCommandHandler


@SpringBootApplication
@ComponentScan("vitta.challenge")
class ControllerApplicationConfig{

    @Bean
    @Autowired
    fun territoryCommandHandler(territoryRepository: CommandTerritoryRepository,
                                planeRepository: CommandPlaneRepository ) =
            TerritoryCommandHandler(territoryRepository = territoryRepository,
                                    planeRepository = planeRepository)
}

fun main(args: Array<String>) {
    SpringApplication.run(ControllerApplicationConfig::class.java, *args)
}