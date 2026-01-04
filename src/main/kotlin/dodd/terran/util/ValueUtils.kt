package dodd.terran.util

import dodd.terran.value.*

operator fun Float.times(coord: Coord) = coord * this

operator fun Float.times(vector: Vector) = vector * this

operator fun Float.times(color: Color) = color * this
