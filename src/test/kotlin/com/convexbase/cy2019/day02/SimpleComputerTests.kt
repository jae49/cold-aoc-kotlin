package com.convexbase.cy2019.day02

import com.convexbase.cy2019.ElfComputer
import kotlin.test.*

class SimpleComputerTests {

    @Test
    fun checkProgramTest1() {
        val program = ElfComputer.getProgramResource("/data/2019/day02/testinput01.txt")
        val result = SimpleComputer.run(program)
        assertTrue(result is SimpleComputer.OpResult.Halt)
        assertEquals(2, program[0])
    }

    @Test
    fun checkProgramTest2() {
        val program = ElfComputer.getProgramResource("/data/2019/day02/testinput02.txt")
        val result = SimpleComputer.run(program)
        assertTrue(result is SimpleComputer.OpResult.Halt)
        assertEquals(6, program[3])
    }

    @Test
    fun checkProgramTest3() {
        val program = ElfComputer.getProgramResource("/data/2019/day02/testinput03.txt")
        val result = SimpleComputer.run(program)
        assertTrue(result is SimpleComputer.OpResult.Halt)
        assertEquals(9801, program[5])
    }

    @Test
    fun checkProgramTest4() {
        val program = ElfComputer.getProgramResource("/data/2019/day02/testinput04.txt")
        val result = SimpleComputer.run(program)
        assertTrue(result is SimpleComputer.OpResult.Halt)
        assertEquals(30, program[0])
        assertEquals(2, program[4])
    }
}