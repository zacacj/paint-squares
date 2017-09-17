package xyz.zawer.paint.squares.ui.application

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class UiController {

    @RequestMapping(value = "/")
    fun index() = "index"

}