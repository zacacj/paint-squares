package vitta.challenge.ui.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vitta.challenge.ui.integration.api.TerritoryApi


@RestController
@RequestMapping("api/territories")
class ProxyController(val territoryApi: TerritoryApi) {

    @GetMapping(path = arrayOf("/bymostpaintedarea"))
    fun getListOfTerritoriesByMostPaintedArea() =
            territoryApi.getOrderedByMostPaintedArea()


    @GetMapping(path = arrayOf("/bymostproportionalpaintedarea"))
    fun getListOfTerritoriesByMostProportionalPaintedArea() =
            territoryApi.getOrderedByMostProportionalPaintedArea()

    @GetMapping(path = arrayOf("/lastfiveerrors"))
    fun getLastFiveErros() =
            listOf(vitta.challenge.representation
                           .TerritoryRepresentation(id = "teste",
                                                    name = "teste",
                                                    start = vitta.challenge.representation.PointRepresentation(
                                                            5, 5
                                                    ),
                                                    end = vitta.challenge.representation.PointRepresentation(
                                                            7, 7
                                                    )
                           )
            )

    @GetMapping(path = arrayOf("/lastfiveadded"))
    fun getLastFiveAddedTerritories() =
            territoryApi.getLastFiveAdded()

    @GetMapping(path = arrayOf("/totals"))
    fun getTotais() =
            territoryApi.getTotais()
}
