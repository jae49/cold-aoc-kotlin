package com.convexbase.cy2024.day04

import com.convexbase.CoreUtils

class D202404Solution {

    companion object {

        fun getPart1Answer():Long {
            val wordSearch = SolutionTools.getWordSearch("/data/2024/day04/input.txt")
            val foundList = wordSearch.search("XMAS")
            return foundList.size.toLong()
        }

        fun getPart2Answer():Long {
            val wordSearch = SolutionTools.getWordSearch("/data/2024/day04/input.txt")
            val foundList = wordSearch.searchXMASShape()
            return foundList.size.toLong()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val part1answer = getPart1Answer()
            println("Part 1: $part1answer")
            val part2answer = getPart2Answer()
            println("Part 2: $part2answer")
        }
    }
}