package com.convexbase.cy2023.day05

import kotlin.test.Test
import kotlin.test.assertEquals


class PuzzleDataTests {

    @Test
    fun testDEntry() {
        val entry = DEntry(
            dest = 50,
            src = 98,
            range = 2
        )
        assertEquals(49, entry[49])
        assertEquals(50, entry[98])
        assertEquals(51, entry[99])
        assertEquals(100, entry[100])
    }
    @Test
    fun testPuzzleData() {
        val lines = PuzzleDataTests::class.java.getResource("/data/2023/day05/testinput01.txt").readText().lines()
        assertEquals(33, lines.size)
        val puzzleData = PuzzleData.parsePuzzleData(lines)
        assertEquals(4, puzzleData.seeds.size)
        assertEquals(27, puzzleData.seedRangesTotalCount)
        assertEquals(2, puzzleData.seedToSoil.rangeList.size)
        assertEquals(3, puzzleData.soilToFertilizer.rangeList.size)
        assertEquals(4, puzzleData.fertilizerToWater.rangeList.size)
        assertEquals(2, puzzleData.waterToLight.rangeList.size)
        assertEquals(3, puzzleData.lightToTemperature.rangeList.size)
        assertEquals(2, puzzleData.temperatureToHumidity.rangeList.size)
        assertEquals(2, puzzleData.humidityToLocation.rangeList.size)
        assertEquals(4, puzzleData.seedLocations.size)
        assertEquals(82, puzzleData.seedLocations[0])
        assertEquals(43, puzzleData.seedLocations[1])
        assertEquals(86, puzzleData.seedLocations[2])
        assertEquals(35, puzzleData.seedLocations[3])
        assertEquals(35, puzzleData.seedLocations.min())
        assertEquals(46, puzzleData.lowestRangeSeedLocation())
    }
}