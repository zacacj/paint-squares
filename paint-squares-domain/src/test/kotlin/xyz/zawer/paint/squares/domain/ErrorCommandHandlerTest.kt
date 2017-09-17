package xyz.zawer.paint.squares.domain

import br.com.zup.eventsourcing.core.Repository
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

class ErrorCommandHandlerTest {

    val errorRepository = mock<Repository<ErrorAggregateRoot>>()
    val errorCommandHandler = ErrorCommandHandler(errorRepository = errorRepository)
    @Test
    fun handleLogError() {
        val commmand = LogError(request = "request",
                                error = "error"
        )
        errorCommandHandler.handle(commmand)
    }

}