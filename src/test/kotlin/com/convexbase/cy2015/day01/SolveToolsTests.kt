package com.convexbase.cy2015.day01

import kotlin.test.Test
import kotlin.test.assertEquals

class SolveToolsTests {

    @Test
    fun testNextFloor() {
        assertEquals(0, SolveTools.nextFloor("(())"))
        assertEquals(0, SolveTools.nextFloor("()()"))
        assertEquals(3, SolveTools.nextFloor("((("))
        assertEquals(3, SolveTools.nextFloor("(()(()("))
        assertEquals(3, SolveTools.nextFloor("))((((("))
        assertEquals(-1, SolveTools.nextFloor("())"))
        assertEquals(-1, SolveTools.nextFloor("))("))
        assertEquals(-3, SolveTools.nextFloor(")))"))
        assertEquals(-3, SolveTools.nextFloor(")())())"))
    }

}