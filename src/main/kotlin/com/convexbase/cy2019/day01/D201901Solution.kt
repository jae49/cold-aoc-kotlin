package com.convexbase.cy2019.day01

class D201901Solution {

    companion object {
        fun getPart1Answer(input: List<String>):Long {
            input.filter { it != ""}.map {FuelCounter.getFuel(it.toLong())}.sum().let { return it}
        }

        fun getPart2Answer(input: List<String>):Long {
            input.filter { it != ""}.map {FuelCounter.getIterativeFuel(it.toLong())}.sum().let { return it}
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val input = D201901Solution::class.java.getResource("/data/2019/day01/input.txt").readText().lines()
            val answer1 = getPart1Answer(input)
            println("Part 1: $answer1")
            val answer2 = getPart2Answer(input)
            println("Part 2: $answer2")
        }
    }
}