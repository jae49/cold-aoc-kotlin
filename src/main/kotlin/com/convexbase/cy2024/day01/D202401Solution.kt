package com.convexbase.cy2024.day01

class D202401Solution {

    companion object {
        fun getPart1Answer():Long {
            val lines = SolutionTools.getLines("/data/2024/day01/input.txt")
            val twoLines = SolutionTools.getNumberLines(lines)
            val differences = SolutionTools.sortAndGetLineDifferences(twoLines)
            return differences.sumOf { it.toLong() }
        }

        fun getPart2Answer():Long {
            val lines = SolutionTools.getLines("/data/2024/day01/input.txt")
            val twoLines = SolutionTools.getNumberLines(lines)
            val similarityLines = SolutionTools.computeSimilarityList(twoLines)
            return similarityLines.sumOf { it.toLong() }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val answer1 = getPart1Answer()
            val answer2 = getPart2Answer()

            println("Part 1: $answer1")
            println("Part 2: $answer2")
        }
    }
}