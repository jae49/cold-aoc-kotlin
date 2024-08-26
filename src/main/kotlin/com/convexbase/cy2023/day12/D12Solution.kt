package com.convexbase.cy2023.day12


class D12Solution {

    companion object {
        fun getPart1Answer(input: List<String>):Long {
            return 0L
        }

        fun getPart2Answer(input: List<String>):Long {
            return 0L
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val input = D12Solution::class.java.getResource("/data/2023/day12/input.txt").readText().lines()
                .filter { it.isNotBlank() }
            val part1Answer = getPart1Answer(input)
            val part2Answer = getPart2Answer(input)
            println("Part 1: $part1Answer")
            println("Part 2: $part2Answer")

        }
    }
}