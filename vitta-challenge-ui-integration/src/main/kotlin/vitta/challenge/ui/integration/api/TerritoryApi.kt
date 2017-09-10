package vitta.challenge.ui.integration.api

import feign.Headers
import feign.RequestLine
import vitta.challenge.representation.TerritoryRepresentation
import vitta.challenge.representation.Totals


@Headers("Accept: application/json", "Content-type: application/json")
interface TerritoryApi {

    @RequestLine("GET /territories?ordered=area")
    fun getOrderedByMostPaintedArea(): List<TerritoryRepresentation>

    @RequestLine("GET /territories?limited=true")
    fun getLastFiveAdded(): List<TerritoryRepresentation>

    @RequestLine("GET /territories?ordered=proportional")
    fun getOrderedByMostProportionalPaintedArea(): List<TerritoryRepresentation>

    @RequestLine("GET /totals")
    fun getTotais(): Totals
}
