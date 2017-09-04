package vitta.challenge.domain


data class Square(val point: Point,
                  val painted: Painted)

data class Painted(val value: Boolean)