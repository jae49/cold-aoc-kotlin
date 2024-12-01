package com.convexbase.cy2024.day01

class SolutionTools {

    companion object {
        fun getLines(resource:String):List<String> {
            return SolutionTools::class.java.getResourceAsStream("$resource").bufferedReader()
                .readLines()
                .filter { it.isNotBlank() }
        }

        fun getNumberLines(lines:List<String>):Pair<List<Int>, List<Int>> {
            val leftLines = mutableListOf<Int>()
            val rightLines = mutableListOf<Int>()
            for (line in lines) {
                val parts = line.trim().split("   ")
                check (parts.size == 2)
                val left = parts[0].toInt()
                val right = parts[1].toInt()
                leftLines.add(left)
                rightLines.add(right)
            }
            return Pair(leftLines, rightLines)
        }

        fun sortAndGetLineDifferences(twoLines: Pair<List<Int>, List<Int>>): List<Int> {
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

        fun computeSimilarityList(twoLines: Pair<List<Int>, List<Int>>): List<Int> {
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