package xyz.zawer.paint.squares.ui.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.zawer.paint.squares.ui.integration.api.TerritoryApi


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
    fun getLastFiveErros() = territoryApi.getLastFiveErros()

    @GetMapping(path = arrayOf("/lastfiveadded"))
    fun getLastFiveAddedTerritories() =
            territoryApi.getLastFiveAdded()

    @GetMapping(path = arrayOf("/totals"))
    fun getTotais() =
            territoryApi.getTotais()
}
