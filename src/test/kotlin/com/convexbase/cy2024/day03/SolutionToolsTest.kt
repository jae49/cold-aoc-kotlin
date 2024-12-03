package com.convexbase.cy2024.day03

import com.convexbase.CoreUtils
import kotlin.test.*

class SolutionToolsTest {

    @Test
    fun testMulExtraction() {
        val line = CoreUtils.readLineFromResource("/data/2024/day03/testinput.txt")
        val pairs = SolutionTools.scanLineForMul(line)
        assertEquals(4, pairs.size)
        assertEquals(2, pairs[0].first)
        assertEquals(4, pairs[0].second)
        assertEquals(5, pairs[1].first)
        assertEquals(5, pairs[1].second)
        assertEquals(11, pairs[2].first)
        assertEquals(8, pairs[2].second)
        assertEquals(8, pairs[3].first)
        assertEquals(5, pairs[3].second)
    }

    @Test fun testMulWhenExtraction() {
        val line = CoreUtils.readLineFromResource("/data/2024/day03/testinput2.txt")
        val pairs = SolutionTools.scanLineForMulAndWhen(line)
        assertEquals(2, pairs.size)
        assertEquals(2, pairs[0].first)
        assertEquals(4, pairs[0].second)
        assertEquals(8, pairs[1].first)
        assertEquals(5, pairs[1].second)

    }
}