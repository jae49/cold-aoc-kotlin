package com.convexbase.cy2015.day02

import com.convexbase.cy2015.day01.Solution

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lines = Solution::class.java.getResource("/data/2015/day02/input.txt").readText().lines()
            println("Lines: ${lines.size}")
            val boxes = SolveTools.getBoxDimensions(lines)
            val totalSquareFeet:Long = boxes.sumOf {
                val (length, width, height) = it
                SolveTools.getWrappingSquareFeet(
                    length = length,
                    width = width,
                    height = height
                ).toLong()
            }
            println("Total square feet of wrapping paper needed: $totalSquareFeet")
            val totalRibbonLength: Long = boxes.sumOf {
                val (length, width, height) = it
                SolveTools.getRibbonLength(
                    length = length,
                    width = width,
                    height = height
                ).toLong()
            }
            println("Total feet of ribbon needed: $totalRibbonLength")
        }
    }
}