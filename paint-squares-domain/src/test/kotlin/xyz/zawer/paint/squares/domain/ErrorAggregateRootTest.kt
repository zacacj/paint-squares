package xyz.zawer.paint.squares.domain

import org.junit.Assert
import org.junit.Test

class ErrorAggregateRootTest {
    @Test
    fun create() {
        val error = ErrorAggregateRoot(request = "test",
                                       error = "error"
        )
        Assert.assertNotNull(error.id)
    }
}