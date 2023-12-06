package com.convexbase.cy2023.day05


class Solution {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val lines = Solution::class.java.getResource("/data/2023/day05/input.txt").readText().lines()
            val puzzleData = PuzzleData.parsePuzzleData(lines)
            println("Seed Count: ${puzzleData.seeds.size}")
            println("Ranged Seed Count: ${puzzleData.seedRangesTotalCount}")
            println("Lowest Seed Location: ${puzzleData.seedLocations.min()}")
            println("Lowest Ranged Seed Location: ${puzzleData.lowestRangeSeedLocation()}")
        }
    }
}