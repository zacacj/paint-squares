package vitta.challenge.command.application

import br.com.zup.eventsourcing.core.config.objectToJson
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import vitta.challenge.command.application.config.ControllerApplicationConfig
import vitta.challenge.command.application.representation.PointRepresentation
import vitta.challenge.command.application.representation.TerritoryRepresentation

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(ControllerApplicationConfig::class))
@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment
        .DEFINED_PORT
)
@AutoConfigureMockMvc
class CommandTerritoryTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun createTerritory() {
        val territoryRepresentation = TerritoryRepresentation(
                name = "First Name",
                start = PointRepresentation(x = 0, y = 0),
                end = PointRepresentation(x = 40, y = 40)
        )
        this.mockMvc.perform(post("/territories").content(territoryRepresentation.objectToJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated())
    }
}


