package com.convexbase.cy2024.day05

import com.convexbase.cy2024.day05.SolutionTools.isValidSection
import com.convexbase.cy2024.day05.SolutionTools.repairInvalid
import kotlin.test.*

class SolutionToolsTest {

    @Test
    fun testPart1() {
        val problem = SolutionTools.getPrintProblemFromRsrc("/data/2024/day05/testinput.txt")
        assertEquals(21, problem.pageRules.size)
        assertEquals(6, problem.pageSections.size)
        val validSections = problem.pageSections.filter {SolutionTools.isValidSection(it, problem.pageRules)}
        assertEquals(3, validSections.size)
        val answer = SolutionTools.getPart1Answer(problem)
        assertEquals(143, answer)
    }

    @Test
    fun testPart2() {
        val problem = SolutionTools.getPrintProblemFromRsrc("/data/2024/day05/testinput.txt")
        val fixed = SolutionTools.repairInvalid(problem.pageSections[3], problem.pageRules)
        assertEquals(97, fixed[0])
        assertEquals(75, fixed[1])
        assertEquals(47, fixed[2])
        assertEquals(5, fixed.size)
        assertEquals(47, SolutionTools.middle(fixed))
        val fixed2 = SolutionTools.fix(problem.pageSections[5], problem.pageRules)
        assertEquals(97, fixed2[0])
        assertEquals(75, fixed2[1])
        assertEquals(47, fixed2[2])
        assertEquals(29, fixed2[3])
        assertEquals(13, fixed2[4])
        val answer = SolutionTools.getPart2Answer(problem)
        val fixedSections = problem.pageSections.filter{ !isValidSection(it, problem.pageRules) }
            .map { repairInvalid(it, problem.pageRules) }
        assertEquals(3, fixedSections.size)
        assertEquals(123, answer)
    }

}