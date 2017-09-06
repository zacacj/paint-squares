package vitta.challenge.command.application.config

import br.com.zup.eventsourcing.core.Repository
import br.com.zup.eventsourcing.core.RepositoryManager
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import vitta.challenge.domain.Territory
import vitta.challenge.domain.TerritoryCommandHandler
import java.net.InetAddress

@SpringBootApplication
@ComponentScan("vitta.challenge")
@Configuration
class ControllerApplicationConfig @Autowired constructor(val repositories: List<Repository<Territory>>) {

    @Bean
    fun territoryCommandHandler() = TerritoryCommandHandler(repositoryManager = RepositoryManager(repositories))
}

private val logger = LogManager.getLogger("vitta.challenge.command.application.config")

fun main(args: Array<String>) {
    val app = SpringApplication.run(ControllerApplicationConfig::class.java, *args)

    val applicationName = app.environment.getProperty("spring.application.name")
    val contextPath = app.environment.getProperty("server.contextPath")
    val port = app.environment.getProperty("server.port")
    val hostAddress = InetAddress.getLocalHost().hostAddress

    logger.info("""|
                   |------------------------------------------------------------
                   |Application '$applicationName' is running! Access URLs:
                   |   Local:      http://127.0.0.1:$port$contextPath
                   |   External:   http://$hostAddress:$port$contextPath
                   |------------------------------------------------------------""".trimMargin())
}