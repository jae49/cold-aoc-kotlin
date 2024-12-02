package com.convexbase.cy2024.day02

import com.convexbase.CoreUtils

class D202402Solution {

    companion object {
        fun getPart1Answer():Long {
            val lines = CoreUtils.readLinesFromResource("/data/2024/day02/input.txt")
            return SolutionTools.checkLevels(lines)
        }

        fun getPart2Answer():Long {
            val lines = CoreUtils.readLinesFromResource("/data/2024/day02/input.txt")
            return 0L
        }


        @JvmStatic
        fun main(args: Array<String>) {
            val answer1 = getPart1Answer()
            val answer2 = getPart2Answer()
            println("Answer1: $answer1")
            println("Answer2: $answer2")
        }
    }
}