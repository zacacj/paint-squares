package xyz.zawer.paint.squares.command.application.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import xyz.zawer.paint.squares.command.repository.CommandErrorRepository
import xyz.zawer.paint.squares.command.repository.CommandPlaneRepository
import xyz.zawer.paint.squares.command.repository.CommandTerritoryRepository
import xyz.zawer.paint.squares.domain.ErrorCommandHandler
import xyz.zawer.paint.squares.domain.TerritoryCommandHandler


@SpringBootApplication
@ComponentScan("xyz.zawer")
class ControllerApplicationConfig{

    @Bean
    @Autowired
    fun territoryCommandHandler(territoryRepository: CommandTerritoryRepository,
                                planeRepository: CommandPlaneRepository) =
            TerritoryCommandHandler(territoryRepository = territoryRepository,
                                    planeRepository = planeRepository)

    @Bean
    @Autowired
    fun errorCommandHandler(errorRepository: CommandErrorRepository) =
            ErrorCommandHandler(errorRepository = errorRepository)
}

fun main(args: Array<String>) {
    SpringApplication.run(ControllerApplicationConfig::class.java, *args)
}