package vitta.challenge.command.application.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan("vitta.challenge")
class ControllerApplicationConfig

fun main(args: Array<String>) {
    SpringApplication.run(ControllerApplicationConfig::class.java, *args)
}