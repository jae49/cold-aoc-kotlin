package com.convexbase.cy2015.day01

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lines = Solution::class.java.getResource("/data/2015/day01/input.txt").readText().lines()
            println("Lines: ${lines.size}")
            val floor = SolveTools.nextFloor(lines[0])
            println("Floor for line 0: $floor")
            val basementIndex = SolveTools.findFirstBasementIndex(lines[0])
            if (basementIndex == null) {
                println("Santa never made it to the basement")
            } else {
                println("Santa made it to the basement on press #$basementIndex")
            }
        }
    }
}