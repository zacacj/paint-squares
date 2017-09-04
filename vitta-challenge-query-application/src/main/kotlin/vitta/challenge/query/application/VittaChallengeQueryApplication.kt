package vitta.challenge.query.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("vitta.challenge")
class VittaChallengeQueryApplication

fun main(args: Array<String>) {
    SpringApplication.run(VittaChallengeQueryApplication::class.java, *args)
}