package com.convexbase.cy2015.day01

class SolveTools {

    companion object {
        fun nextFloor(line: String):Int {
            var floor = 0
            line.toCharArray().forEach { press ->
                when (press) {
                    '(' -> floor += 1
                    ')' -> floor -= 1
                    else -> throw IllegalArgumentException("Invalid button press $press")
                }
            }
            return floor
        }

        // 1 based index according to the instructions
        fun findFirstBasementIndex(line: String): Int? {
            var floor = 0
            line.toCharArray().forEachIndexed() { index, press ->
                when (press) {
                    '(' -> floor += 1
                    ')' -> floor -= 1
                    else -> throw IllegalArgumentException("Invalid button press $press")
                }
                if (floor == -1) return index + 1
            }
            return null
        }
    }
}