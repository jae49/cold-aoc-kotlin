package com.convexbase.cy2023.day09

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = Solution::class.java.getResource("/data/2023/day09/input.txt").readText().lines()
                .filter { it.isNotBlank() }
            val part1Answer = SolveTools.getPart1Answer(input)
            val part2Answer = SolveTools.getPart2Answer(input)
            println("Part 1: $part1Answer")
            println("Part 2: $part2Answer")
        }

    }
}