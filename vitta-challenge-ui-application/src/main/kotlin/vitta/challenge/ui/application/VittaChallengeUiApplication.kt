package vitta.challenge.ui.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("vitta.challenge")
class VittaChallengeUiApplication

fun main(args: Array<String>) {
    SpringApplication.run(VittaChallengeUiApplication::class.java, *args)
}
