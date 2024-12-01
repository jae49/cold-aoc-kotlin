package com.convexbase.cy2024.day01

import kotlin.test.*


class SolutionToolsTest {

    @Test
    fun checkLinesAndDifferences() {
        val lines = SolutionTools.getLines("/data/2024/day01/testinput.txt")
        val twoLines = SolutionTools.getNumberLines(lines)
        val differences = SolutionTools.sortAndGetLineDifferences(twoLines)
        assertEquals(2, differences[0])
        assertEquals(1, differences[1])
    }

    @Test
    fun checkSimilarityLines() {
        val lines = SolutionTools.getLines("/data/2024/day01/testinput.txt")
        val twoLines = SolutionTools.getNumberLines(lines)
        val similarityLines = SolutionTools.computeSimilarityList(twoLines)
        assertEquals(6, similarityLines.size)
        assertEquals(9, similarityLines[0])
        assertEquals(4, similarityLines[1])
        assertEquals(0, similarityLines[2])
        assertEquals(0, similarityLines[3])
        assertEquals(9, similarityLines[4])
        assertEquals(9, similarityLines[5])
    }
}