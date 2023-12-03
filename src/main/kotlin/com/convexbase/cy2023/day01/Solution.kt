package com.convexbase.cy2023.day01

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val total = SolveTools.totalFLNumberStringsInFile("/data/day01/input.txt")
            println("Total of normally interpreted input is: $total")
            val uglyTotal = SolveTools.totalUglyFLNumberStringsInFile("/data/day01/input.txt")
            println("Total of ugly interpreted input is: $uglyTotal")
        }
    }
}