package com.convexbase.cy2023.day02

import kotlin.test.Test
import kotlin.test.assertEquals

class GameTests {

    @Test
    fun testGameMaxGrabAndCube() {
        val gameText = SolveToolsTests::class.java.getResource("/data/2023/day02/testinput01.txt").readText()
        val games = SolveTools.loadGames(gameText)
        assertEquals(5, games.size)

        assertEquals(4, games[0].maxGrab.red)
        assertEquals(2, games[0].maxGrab.green)
        assertEquals(6, games[0].maxGrab.blue)
        assertEquals(48, games[0].maxCube)

        assertEquals(1, games[1].maxGrab.red)
        assertEquals(3, games[1].maxGrab.green)
        assertEquals(4, games[1].maxGrab.blue)
        assertEquals(12, games[1].maxCube)

        assertEquals(20, games[2].maxGrab.red)
        assertEquals(13, games[2].maxGrab.green)
        assertEquals(6, games[2].maxGrab.blue)
        assertEquals(1560, games[2].maxCube)

        assertEquals(14, games[3].maxGrab.red)
        assertEquals(3, games[3].maxGrab.green)
        assertEquals(15, games[3].maxGrab.blue)
        assertEquals(630, games[3].maxCube)

        assertEquals(6, games[4].maxGrab.red)
        assertEquals(3, games[4].maxGrab.green)
        assertEquals(2, games[4].maxGrab.blue)
        assertEquals(36, games[4].maxCube)

    }

}