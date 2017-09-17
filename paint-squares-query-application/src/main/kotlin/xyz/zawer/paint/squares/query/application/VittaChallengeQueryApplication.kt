package xyz.zawer.paint.squares.query.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import xyz.zawer.paint.squares.query.event.handler.ErrorSubscription
import xyz.zawer.paint.squares.query.event.handler.TerritorySubscription
import xyz.zawer.paint.squares.query.repository.MongoConfig
import javax.annotation.PostConstruct


@ComponentScan("xyz.zawer")
@Import(MongoConfig::class)
@SpringBootApplication(exclude = arrayOf(MongoDataAutoConfiguration::class))
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