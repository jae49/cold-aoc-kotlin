package com.convexbase.cy2023.day03

import com.convexbase.cy2023.day02.SolveToolsTests
import kotlin.test.Test
import kotlin.test.assertEquals


class SchematicLineTests {

    @Test
    fun testToSchematicLine() {
        val schematicLines = SolveToolsTests::class.java.getResource("/day03/testinput01.txt").readText().lines()
        assertEquals(10, schematicLines.size)
        expectSchematicLine(0, schematicLines[0], 0, 2)
        expectSchematicLine(1, schematicLines[1], 1, 0)
        expectSchematicLine(2, schematicLines[2], 0, 2)
        expectSchematicLine(3, schematicLines[3], 1, 0)
        expectSchematicLine(4, schematicLines[4], 1, 1)
        expectSchematicLine(5, schematicLines[5], 1, 1)
        expectSchematicLine(6, schematicLines[6], 0, 1)
        expectSchematicLine(7, schematicLines[7], 0, 1)
        expectSchematicLine(8, schematicLines[8], 2, 0)
        expectSchematicLine(9, schematicLines[9], 0, 2)
    }

    fun expectSchematicLine(lineNo: Int, line: String, partCount: Int, partNumCount:Int) {
        val schematicLine = SchematicLine.toSchematicLine(lineNo, line)
        assertEquals(partCount, schematicLine.parts.size)
        assertEquals(partNumCount, schematicLine.partNumbers.size)
    }

    @Test
    fun testGetPartNumbers() {
        // test a line with part numbers only
        val testPNOLine = "467..114.."
        val testPNOPartNumbers = SchematicLine.getPartNumbers(0, testPNOLine)
        assertEquals(2, testPNOPartNumbers.size)

        assertEquals(0, testPNOPartNumbers[0].startIndex)
        assertEquals(2, testPNOPartNumbers[0].endIndex)
        assertEquals(467, testPNOPartNumbers[0].partNo)

        assertEquals(5, testPNOPartNumbers[1].startIndex)
        assertEquals(7, testPNOPartNumbers[1].endIndex)
        assertEquals(114, testPNOPartNumbers[1].partNo)

        // test a line with part numbers at the end
        val testPELine = "467..114..115"
        val testPENumbers = SchematicLine.getPartNumbers(0, testPELine)
        assertEquals(3, testPENumbers.size)

        assertEquals(10, testPENumbers[2].startIndex)
        assertEquals(12, testPENumbers[2].endIndex)
        assertEquals(115, testPENumbers[2].partNo)

        // test a line with mixed part numbers and symbols
        val testMPNLine= "617*......"
        val testMPNNumbers = SchematicLine.getPartNumbers(0, testMPNLine)
        assertEquals(1, testMPNNumbers.size)

        assertEquals(0, testMPNNumbers[0].startIndex)
        assertEquals(2, testMPNNumbers[0].endIndex)
        assertEquals(617, testMPNNumbers[0].partNo)

    }

    @Test
    fun testGetParts() {
        // test a line with parts only
        val testPOLine = "...*......"
        val testPOParts = SchematicLine.getParts(0, testPOLine)
        assertEquals(1, testPOParts.size)

        assertEquals('*', testPOParts[0].symbol)
        assertEquals(3, testPOParts[0].index)

        // test a line with mixed part numbers and symbols
        val testMPNLine= "617*.....#3$"
        val testMPNParts = SchematicLine.getParts(0, testMPNLine)
        assertEquals(3, testMPNParts.size)

        assertEquals('*', testMPNParts[0].symbol)
        assertEquals(3, testMPNParts[0].index)
        assertEquals('#', testMPNParts[1].symbol)
        assertEquals(9, testMPNParts[1].index)
        assertEquals('$', testMPNParts[2].symbol)
        assertEquals(11, testMPNParts[2].index)

    }
}