package com.convexbase.cy2023.day10

class D10Solution {

    companion object {

        fun getPart1and2Answers(input: List<String>):Pair<Int, Int> {
            val sketch = D10SolveTools.getSketch(input).getPureSketch()
            sketch.markVisibilityUsingPipes()
            val maxDistance = sketch.startPoints[0].maxDistance
            val insideTiles = sketch.points.filter {it.visibility == VType.INNER}.count()
            return Pair(maxDistance, insideTiles)
        }



        @JvmStatic
        fun main(args: Array<String>) {
            val input = D10Solution::class.java.getResource("/data/2023/day10/input.txt").readText().lines()
                .filter { it.isNotBlank() }
            val (part1Answer, part2Answer) = getPart1and2Answers(input)
            println("Part 1: $part1Answer")
            println("Part 2: $part2Answer")

        }
    }
}