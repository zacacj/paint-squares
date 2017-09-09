package vitta.challenge.ui.integration.api

import feign.Headers
import feign.RequestLine
import vitta.challenge.representation.TerritoryRepresentation


@Headers("Accept: application/json", "Content-type: application/json")
interface TerritoryApi {

    @RequestLine("GET /territories")
    fun getOrderedByMostPaintedArea(): List<TerritoryRepresentation>
}
