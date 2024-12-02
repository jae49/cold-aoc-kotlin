package com.convexbase.cy2024.day02

import com.convexbase.CoreUtils
import kotlin.test.*

class SolutionToolsTest {
    @Test
    fun runTest1() {
        val lines = CoreUtils.readLinesFromResource("/data/2024/day02/testinput.txt")
        val safelines = SolutionTools.checkLevels(lines)
        assertEquals(2, safelines)
        val dampedSafeLines = SolutionTools.checkedDampedLevels(lines)
        assertEquals(4, dampedSafeLines)
    }
}