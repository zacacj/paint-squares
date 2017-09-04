package vitta.challenge.query.repository.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import vitta.challenge.query.repository.repositories.SquareRepository
import vitta.challenge.query.repository.repositories.TerritoryRepository


@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = arrayOf(TerritoryRepository::class,SquareRepository::class))
class MongoConfig : AbstractReactiveMongoConfiguration() {

    @Bean
    override fun mongoClient(): MongoClient {
        return MongoClients.create()
    }

    override fun getDatabaseName(): String {
        return "test"
    }

    @Bean
    override fun reactiveMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(mongoClient(), databaseName)
    }

}