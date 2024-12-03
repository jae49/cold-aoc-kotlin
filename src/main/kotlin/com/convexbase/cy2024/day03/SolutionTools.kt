package com.convexbase.cy2024.day03

import com.convexbase.CoreUtils

class SolutionTools {

    companion object {

        fun scanLineForMul(line: String):List<Pair<Int,Int>> {
            val pairs = mutableListOf<Pair<Int, Int>>()
            val regex = """mul\((?<x>\d{1,3}),(?<y>\d{1,3})\)""".toRegex()
            var matchresult = regex.find(line)
            while (matchresult != null) {
                val x = matchresult.groups["x"]!!.value.toInt()
                val y = matchresult.groups["y"]!!.value.toInt()
                pairs.add(Pair(x, y))
                matchresult = matchresult.next()
            }
            return pairs
        }

        fun scanLineForMulAndWhen(line: String):List<Pair<Int,Int>> {
            val pairs = mutableListOf<Pair<Int, Int>>()
            val regex = """(?<do>do\(\))|(?<dont>don\'t\(\))|mul\((?<x>\d{1,3}),(?<y>\d{1,3})\)""".toRegex()
            var matchresult = regex.find(line)
            var validmults = true
            while (matchresult != null) {
                when {
                    matchresult.groups["do"] != null -> validmults = true
                    matchresult.groups["dont"] != null -> validmults = false
                    matchresult.groups["x"] != null -> {
                        val x = matchresult.groups["x"]!!.value.toInt()
                        val y = matchresult.groups["y"]!!.value.toInt()
                        if (validmults) {
                            pairs.add(Pair(x, y))
                        }
                    }
                }
                matchresult = matchresult.next()
            }
            return pairs
        }

        fun sumPairs(pairs: List<Pair<Int,Int>>):Long = pairs.sumOf { (it.first * it.second).toLong() }

    }
}