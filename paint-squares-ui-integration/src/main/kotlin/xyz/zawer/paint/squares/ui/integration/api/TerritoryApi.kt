package xyz.zawer.paint.squares.ui.integration.api

import feign.Headers
import feign.RequestLine
import xyz.zawer.paint.squares.representation.ErrorRepresentation
import xyz.zawer.paint.squares.representation.TerritoryRepresentation
import xyz.zawer.paint.squares.representation.Totals


@Headers("Accept: application/json", "Content-type: application/json")
interface TerritoryApi {

    @RequestLine("GET /territories?ordered=area")
    fun getOrderedByMostPaintedArea(): List<TerritoryRepresentation>

    @RequestLine("GET /territories?limited=true")
    fun getLastFiveAdded(): List<TerritoryRepresentation>

    @RequestLine("GET /territories?ordered=proportional")
    fun getOrderedByMostProportionalPaintedArea(): List<TerritoryRepresentation>

    @RequestLine("GET /errors?limited=true")
    fun getLastFiveErros(): List<ErrorRepresentation>

    @RequestLine("GET /totals")
    fun getTotais(): Totals
}
