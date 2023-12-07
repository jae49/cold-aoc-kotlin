package com.convexbase.cy2023.day06

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = Solution::class.java.getResource("/data/2023/day06/input.txt").readText().lines()
                .filter{ it.isNotBlank()}

            //part 1
            val part1Races = SolveTools.parseForPart1(input)
            println("Part 1 Race Count: ${part1Races.size}")
            val part1RaceDistances = SolveTools.getWinningDistanceCounts(part1Races)
            val part1Computation = SolveTools.getWinningRaceCountComputation(part1RaceDistances)
            println("Part 1 Winning Race Computation: $part1Computation")

            // part 2
            val part2Races = listOf(SolveTools.parseForPart2(input))
            println("Part 2 Race Count: ${part2Races.size}")
            val part2RaceDistances = SolveTools.getWinningDistanceCounts(part2Races)
            val part2Computation = SolveTools.getWinningRaceCountComputation(part2RaceDistances)
            println("Part 2 Winning Race Computation: $part2Computation")
        }
    }
}