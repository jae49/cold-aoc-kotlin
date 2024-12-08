package com.convexbase.cy2024.day05

import com.convexbase.cy2023.day09.Solution

class D202405Solution {

    companion object {
        fun getPart1Answer():Long {
            val problem = SolutionTools.getPrintProblemFromRsrc("/data/2024/day05/input.txt")
            return SolutionTools.getPart1Answer(problem)
        }

        fun getPart2Answer():Long {
            val problem = SolutionTools.getPrintProblemFromRsrc("/data/2024/day05/input.txt")
            return SolutionTools.getPart2Answer(problem)
        }


        @JvmStatic
        fun main(args: Array<String>) {
            val answer1 = getPart1Answer()
            val answer2 = getPart2Answer()
            println("Answer 1: $answer1")
            println("Answer 2: $answer2")
        }
    }
}