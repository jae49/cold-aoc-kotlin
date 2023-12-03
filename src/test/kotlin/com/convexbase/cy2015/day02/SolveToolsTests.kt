package com.convexbase.cy2015.day02

import kotlin.test.Test
import kotlin.test.assertEquals


class SolveToolsTests {

    @Test
    fun testGetWrappingSquareFeet() {
        assertEquals(58, SolveTools.getWrappingSquareFeet(length=2, width=3, height=4))
        assertEquals(43, SolveTools.getWrappingSquareFeet(length=1, width = 1, height = 10))
    }

    fun testGetRibbonLength() {
        assertEquals(34, SolveTools.getRibbonLength(length = 2, width = 3, height=4))
        assertEquals(14, SolveTools.getRibbonLength(length = 1, width = 1, height = 10))
    }

}