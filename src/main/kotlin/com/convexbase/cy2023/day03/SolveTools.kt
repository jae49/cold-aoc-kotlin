package com.convexbase.cy2023.day03

import kotlin.math.absoluteValue

class SolveTools {

    companion object {

        fun getSchematicLines(lines: List<String>): List<SchematicLine> {
            val schematicLines = mutableListOf<SchematicLine>()
            for (lineNo in 0.rangeUntil(lines.size)) {
                val line = lines[lineNo]
                schematicLines.add(SchematicLine.toSchematicLine(lineNo, line))
            }
            return schematicLines
        }

        fun findPartNumbersNearSymbols(lines: List<SchematicLine>):List<PartNo> {
            val partNumbersNearSymbols = mutableListOf<PartNo>()
            for (lineNo in 0.rangeUntil(lines.size)) {
                val lineAbove = if (lineNo < 1) null else lines[lineNo-1]
                val lineBelow = if (lineNo > lines.size - 2) null else lines[lineNo+1]
                val line = lines[lineNo]
                for (partNo in line.partNumbers) {
                    if (isPartNumberNearSymbol(partNo, line, lineAbove, lineBelow)) partNumbersNearSymbols.add(partNo)
                }
            }
            return partNumbersNearSymbols
        }

        fun getListOfGearRatios(lines:List<SchematicLine>): List<Long> {
            val matchingGears = getGearsNearTwoPartNumbers(lines)
            val gearRatioList = mutableListOf<Long>()
            for ((part, partNo1, partNo2) in matchingGears) {
                val gearRatio:Long = partNo1.partNo.toLong() * partNo2.partNo.toLong()
                gearRatioList.add(gearRatio)
            }
            return gearRatioList
        }

        fun getGearsNearTwoPartNumbers(lines: List<SchematicLine>):List<Triple<PartSymbol, PartNo, PartNo>> {
            val matchingGears = mutableListOf<Triple<PartSymbol, PartNo, PartNo>>()
            for (lineNo in 0.rangeUntil(lines.size)) {
                val lineAbove = if (lineNo < 1) null else lines[lineNo-1]
                val lineBelow = if (lineNo > lines.size - 2) null else lines[lineNo+1]
                val line = lines[lineNo]
                //only search through the gears
                for (part in line.parts.filter {it.symbol == '*'}) {
                    val partNumbers = getPartNumbersNearPart(part, line, lineAbove, lineBelow)
                    if (partNumbers.size == 2) matchingGears.add(Triple(part, partNumbers[0], partNumbers[1]))
                }
            }
            return matchingGears
        }

        fun getPartNumbersNearPart(
            part: PartSymbol,
            line: SchematicLine,
            lineAbove: SchematicLine?,
            lineBelow: SchematicLine?
        ): List<PartNo> {
            val partNumbers = mutableListOf<PartNo>()
            for (partNumber in line.partNumbers) {
                if (partNumberNear(part, partNumber)) partNumbers.add(partNumber)
            }
            for (partNumber in lineAbove?.partNumbers?:listOf()) {
                if (partNumberNear(part, partNumber)) partNumbers.add(partNumber)
            }
            for (partNumber in lineBelow?.partNumbers?:listOf()) {
                if (partNumberNear(part, partNumber)) partNumbers.add(partNumber)
            }
            return partNumbers
        }

        fun partNumberNear(part: PartSymbol, partNumber: PartNo):Boolean {
            for (digitIndex in partNumber.startIndex..partNumber.endIndex) {
                if ((digitIndex - part.index).absoluteValue < 2) return true
            }
            return false
        }

        fun isPartNumberNearSymbol(
            partNo: PartNo,
            line: SchematicLine,
            lineAbove: SchematicLine?,
            lineBelow: SchematicLine?
        ):Boolean {
            //check to the left and right of the part on the same line
            for (part in line.parts) {
                if (part.index >= (partNo.startIndex-1)  && part.index <= (partNo.endIndex+1)) return true
            }
            // check line above
            for (part in lineAbove?.parts?:listOf()) {
                if (part.index >= (partNo.startIndex-1)  && part.index <= (partNo.endIndex+1)) return true
            }
            //check line below
            for (part in lineBelow?.parts?:listOf()) {
                if (part.index >= (partNo.startIndex-1)  && part.index <= (partNo.endIndex+1)) return true
            }
            return false
        }

    }
}