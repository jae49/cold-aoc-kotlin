package com.convexbase.cy2023.day08


class Solution {

    companion object {
        fun getPart1and2Answers(input: List<String>):Pair<Long, Long> {
            val theMap = LRMap.toLRMap(input)
            val part1Answer = theMap.followSinglePath("AAA").second
            val counts = theMap.followAllPaths(theMap.allStarts()).map { it.second}
            val part2Answer = SolveTools.lcm(counts)
            return Pair(part1Answer, part2Answer)
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val input = Solution::class.java.getResource("/data/2023/day08/input.txt").readText().lines()
                .filter { it.isNotBlank() }
            val (part1Answer, part2Answer) = getPart1and2Answers(input)
            println("Part 1: $part1Answer")
            println("Part 2: $part2Answer")

        }
    }
}