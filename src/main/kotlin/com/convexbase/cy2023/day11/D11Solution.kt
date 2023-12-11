package com.convexbase.cy2023.day11

class D11Solution {

    companion object {

        fun getPart1Answer(input:List<String>):Long {
            val theMap = GMap.getGMap(input)
            theMap.expand(1)
            return theMap.allDistances.sum()
        }

        fun getPart2Answer(input:List<String>):Long {
            val theMap = GMap.getGMap(input)
            theMap.expand(1_000_000 - 1)
            return theMap.allDistances.sum()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val input = D11Solution::class.java.getResource("/data/2023/day11/input.txt").readText().lines()
                .filter { it.isNotBlank() }
            val part1Answer = getPart1Answer(input)
            val part2Answer = getPart2Answer(input)
            println("Part 1: $part1Answer")
            println("Part 2: $part2Answer")

        }
    }
}