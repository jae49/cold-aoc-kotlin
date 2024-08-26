package com.convexbase.cy2023.day12

import kotlin.math.pow
class D12SolveTools {


    companion object {
        fun totalCombinations(base: Int, symbolCount: Int): Long =
            base.toDouble().pow(symbolCount.toDouble()).toLong()


    }
}
