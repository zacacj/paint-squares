package xyz.zawer.paint.squares.ui.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("xyz.zawer")
class PaintSquaresUiApplication

fun main(args: Array<String>) {
    SpringApplication.run(PaintSquaresUiApplication::class.java, *args)
}
