package com.convexbase.cy2023.day03

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lines = Solution::class.java.getResource("/data/day03/input.txt").readText().lines()
            val schematicLines = SolveTools.getSchematicLines(lines)
            val partNumbers = SolveTools.findPartNumbersNearSymbols(schematicLines)
            val partNumbersSum = partNumbers.sumOf { it.partNo }
            println("Sum of part numbers near parts: $partNumbersSum")
            val gearRatiosSum = SolveTools.getListOfGearRatios(schematicLines).sumOf{ it }
            println("Sum of gear ratios: $gearRatiosSum")

        }
    }
}