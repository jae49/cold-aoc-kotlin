package com.convexbase.cy2024.day03

import com.convexbase.CoreUtils

class D202403Solution {

    companion object {

        fun getPart1Answer():Long {
            val line = CoreUtils.readLineFromResource("/data/2024/day03/input.txt")
            val pairs = SolutionTools.scanLineForMul(line)
            return SolutionTools.sumPairs(pairs)
        }

        fun getPart2Answer():Long {
            val line = CoreUtils.readLineFromResource("/data/2024/day03/input.txt")
            val pairs = SolutionTools.scanLineForMulAndWhen(line)
            return SolutionTools.sumPairs(pairs)
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