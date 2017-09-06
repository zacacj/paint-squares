package vitta.challenge.command.application

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import vitta.challenge.command.application.config.ControllerApplicationConfig


@RunWith(SpringRunner::class)
@ContextConfiguration(classes = arrayOf(ControllerApplicationConfig::class))
@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment
        .DEFINED_PORT
)
@AutoConfigureMockMvc
class CommandSquareTests {

    @Test
    fun setup(){

    }

}


