package vitta.challenge.query.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import vitta.challenge.query.event.handler.ErrorSubscription
import vitta.challenge.query.event.handler.TerritorySubscription
import vitta.challenge.query.repository.MongoConfig
import javax.annotation.PostConstruct


@ComponentScan("vitta.challenge")
@Import(MongoConfig::class)
@SpringBootApplication(exclude = arrayOf(MongoAutoConfiguration::class, MongoDataAutoConfiguration::class))
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration::class)
class AppConfig {
    @Autowired
    lateinit var territorySubscription: TerritorySubscription

    @Autowired
    lateinit var errorSubscription: ErrorSubscription

    @PostConstruct
    fun initSubscribe() {
        territorySubscription.start()
        errorSubscription.start()
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(AppConfig::class.java, *args)
}