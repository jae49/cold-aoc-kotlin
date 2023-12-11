package com.convexbase.cy2023.day11

data class Galaxy(
    val id: Int,
    var x: Long,
    var y: Long
) {
    val gcode:String
        get() = getGCode(x, y)

    fun shiftRight(amount: Long) { x += amount}
    fun shiftDown(amount: Long) {y += amount}

    companion object {
        val galaxyChar:Char = '#'
        fun getGCode(x:Long, y:Long):String {
            return "r${x}c${y}"
        }

        fun distanceBetween(first: Galaxy, second:Galaxy):Long {
            val xDistance = if (first.x > second.x) first.x - second.x else second.x - first.x
            val yDistance = if (first.y > second.y) first.y - second.y else second.y - first.y
            return xDistance + yDistance
        }

    }
}