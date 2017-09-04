package vitta.challenge.domain


data class Square(val point: Point,
                  val paitend: Painted)

data class Painted(val value: Boolean)