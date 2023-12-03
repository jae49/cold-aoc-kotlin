package com.convexbase.cy2023.day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SolveToolsTests {

    @Test
    fun check_getFirstLastNumber() {
        assertEquals(12, SolveTools.getFirstLastNumber("1abc2"))
        assertEquals(38, SolveTools.getFirstLastNumber("pqr3stu8vwx"))
        assertEquals(15, SolveTools.getFirstLastNumber("a1b2c3d4e5f"))
        assertEquals(77, SolveTools.getFirstLastNumber("treb7uchet"))
        assertEquals(0, SolveTools.getFirstLastNumber("NoNumbersHere:("))
    }

    @Test
    fun check_getTotal() {
        assertEquals(296, SolveTools.totalFLNumberStringsInFile("/data/2023/day01/testinput01.txt"))
    }

    @Test
    fun check_getUglyNumber() {
        assertEquals('0', SolveTools.getUglyNumber(0,"zero".toCharArray()))
        assertEquals('1', SolveTools.getUglyNumber(0,"one".toCharArray()))
        assertEquals('2', SolveTools.getUglyNumber(0,"two".toCharArray()))
        assertEquals('3', SolveTools.getUglyNumber(0,"three".toCharArray()))
        assertEquals('4', SolveTools.getUglyNumber(0,"four".toCharArray()))
        assertEquals('5', SolveTools.getUglyNumber(0,"five".toCharArray()))
        assertEquals('6', SolveTools.getUglyNumber(0,"six".toCharArray()))
        assertEquals('7', SolveTools.getUglyNumber(0,"seven".toCharArray()))
        assertEquals('8', SolveTools.getUglyNumber(0,"eight".toCharArray()))
        assertEquals('9', SolveTools.getUglyNumber(0,"nine".toCharArray()))
        assertEquals('9', SolveTools.getUglyNumber(0,"9zero".toCharArray()))
        assertEquals('8', SolveTools.getUglyNumber(0,"8one".toCharArray()))
        assertEquals('7', SolveTools.getUglyNumber(0,"7two".toCharArray()))
        assertEquals('6', SolveTools.getUglyNumber(0,"6three".toCharArray()))
        assertEquals('5', SolveTools.getUglyNumber(0,"5four".toCharArray()))
        assertEquals('4', SolveTools.getUglyNumber(0,"4five".toCharArray()))
        assertEquals('3', SolveTools.getUglyNumber(0,"3six".toCharArray()))
        assertEquals('2', SolveTools.getUglyNumber(0,"2seven".toCharArray()))
        assertEquals('1', SolveTools.getUglyNumber(0,"1eight".toCharArray()))
        assertEquals('0', SolveTools.getUglyNumber(0,"0nine".toCharArray()))
    }
}