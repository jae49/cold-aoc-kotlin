package com.convexbase.cy2024.day01

import com.convexbase.CoreUtils

typealias IntListPair = Pair<List<Int>, List<Int>>
typealias IntList = List<Int>

class SolutionTools {

    companion object {
        fun getNumberLines(lines:List<String>):IntListPair {
            val leftLines = mutableListOf<Int>()
            val rightLines = mutableListOf<Int>()
            for (line in lines) {
                val (left, right) = CoreUtils.getLineLongs(line, 2)
                leftLines.add(left.toInt())
                rightLines.add(right.toInt())
            }
            return Pair(leftLines, rightLines)
        }

        fun sortAndGetLineDifferences(twoLines: IntListPair):IntList {
            val (leftLines, rightLines) = twoLines
            check (leftLines.size == rightLines.size)
            val differences = mutableListOf<Int>()
            val leftSortedLines = leftLines.sorted()
            val rightSortedLines = rightLines.sorted()
            for (i in leftSortedLines.indices) {
                val difference = Math.abs(leftSortedLines[i] - rightSortedLines[i]).toInt()
                differences.add(difference)
            }
            return differences
        }

        fun computeSimilarityList(twoLines: IntListPair):IntList {
            val (leftLines, rightLines) = twoLines
            val mapCount = mutableMapOf<Int,Int>()
            for (num in rightLines) {
                val count = mapCount.getOrDefault(num,0)
                mapCount[num] = count + 1
            }
            val similarityLines = mutableListOf<Int>()
            for (num in leftLines) {
                similarityLines.add(num * mapCount.getOrDefault(num,0))
            }
            return similarityLines
        }
    }
}