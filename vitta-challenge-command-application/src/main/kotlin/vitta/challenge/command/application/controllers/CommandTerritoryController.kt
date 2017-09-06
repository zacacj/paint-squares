package vitta.challenge.command.application.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vitta.challenge.command.application.representation.TerritoryRepresentation
import vitta.challenge.domain.CreateTerritory
import vitta.challenge.domain.Name
import vitta.challenge.domain.Point
import vitta.challenge.domain.TerritoryCommandHandler
import javax.validation.Valid

@RestController
@RequestMapping("/territories", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
class CommandTerritoryController(val territoryCommandHandler: TerritoryCommandHandler) {

    @PostMapping
    fun create(@RequestBody @Valid request: TerritoryRepresentation): ResponseEntity<TerritoryRepresentation> {
        val command = CreateTerritory(name = Name(request.name),
                                      start = Point(request.start.x, request.start.y),
                                      end = Point(request.end.x, request.end.y)
        )
        val territory = territoryCommandHandler.handle(command)
        return ResponseEntity(
                TerritoryRepresentation.fromDomain(territory), HttpStatus.CREATED)
    }
}