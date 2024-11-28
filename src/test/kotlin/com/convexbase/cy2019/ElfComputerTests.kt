package com.convexbase.cy2019

import kotlin.test.Test
import kotlin.test.assertEquals



class ElfComputerTests {

    @Test
    fun programLoadTest() {
        val program = ElfComputer.getProgramResource("/data/2019/day02/testinput.txt")
        assertEquals(12, program.size)
        assertEquals(50, program[11])
    }

}