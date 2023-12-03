package com.convexbase.cy2023.day03

import com.convexbase.cy2023.day02.SolveToolsTests
import kotlin.test.Test
import kotlin.test.assertEquals

class SolveToolsTests {
    @Test
    fun testFindPartNumbersNearSymbols() {
        val lines = SolveToolsTests::class.java.getResource("/day03/testinput01.txt").readText().lines()
        val schematicLines = SolveTools.getSchematicLines(lines)
        assertEquals(10, schematicLines.size)
        val partsNearSymbols = SolveTools.findPartNumbersNearSymbols(schematicLines)
        assertEquals(8, partsNearSymbols.size)
        assertEquals(4361, partsNearSymbols.sumOf { it.partNo })
    }

    @Test
    fun testGetGearsNearTwoPartNumbers() {
        val lines = SolveToolsTests::class.java.getResource("/day03/testinput01.txt").readText().lines()
        val schematicLines = SolveTools.getSchematicLines(lines)
        assertEquals(10, schematicLines.size)
        val matchingGears = SolveTools.getGearsNearTwoPartNumbers(schematicLines)
        assertEquals(2, matchingGears.size)
        assertEquals(1, matchingGears[0].first.lineNo)
        assertEquals(3, matchingGears[0].first.index)
        assertEquals(8, matchingGears[1].first.lineNo)
        assertEquals(5, matchingGears[1].first.index)
        val testGearRatioSums = SolveTools.getListOfGearRatios(schematicLines).sumOf { it }
        assertEquals(467835L, testGearRatioSums)
    }
}