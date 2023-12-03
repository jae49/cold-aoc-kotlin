package com.convexbase.cy2023.day02

import kotlin.test.Test
import kotlin.test.assertEquals

class SolveToolsTests {

    @Test
    fun testGameLoad() {
        val gameText = SolveToolsTests::class.java.getResource("/day02/testinput01.txt").readText()
        val games = SolveTools.loadGames(gameText)
        assertEquals(5, games.size)
        // check game 0
        assertEquals(1, games[0].id)
        assertEquals(3, games[0].grabs.size)
        assertEquals(3, games[0].grabs[0].blue)
        assertEquals(4, games[0].grabs[0].red)
        assertEquals(0, games[0].grabs[0].green)
        assertEquals(1, games[0].grabs[1].red)
        assertEquals(2, games[0].grabs[1].green)
        assertEquals(6, games[0].grabs[1].blue)
        assertEquals(2, games[0].grabs[2].green)
        assertEquals(0, games[0].grabs[2].red)
        assertEquals(0, games[0].grabs[2].blue)
        //check game 3
        assertEquals(4, games[3].id)
        assertEquals(3, games[3].grabs.size)
        assertEquals(6, games[3].grabs[0].blue)
        assertEquals(3, games[3].grabs[0].red)
        assertEquals(1, games[3].grabs[0].green)
        assertEquals(6, games[3].grabs[1].red)
        assertEquals(3, games[3].grabs[1].green)
        assertEquals(0, games[3].grabs[1].blue)
        assertEquals(3, games[3].grabs[2].green)
        assertEquals(14, games[3].grabs[2].red)
        assertEquals(15, games[3].grabs[2].blue)

        //check game 4
        assertEquals(5, games[4].id)
        assertEquals(2, games[4].grabs.size)
        assertEquals(1, games[4].grabs[0].blue)
        assertEquals(6, games[4].grabs[0].red)
        assertEquals(3, games[4].grabs[0].green)
        assertEquals(1, games[4].grabs[1].red)
        assertEquals(2, games[4].grabs[1].green)
        assertEquals(2, games[4].grabs[1].blue)
    }

    @Test
    fun testPossibleGames() {
        val gameText = SolveToolsTests::class.java.getResource("/day02/testinput01.txt").readText()
        val games = SolveTools.loadGames(gameText)
        assertEquals(5, games.size)
        val possibleGames = SolveTools.possibleGamesWithMaxColors(
            redMax = 12,
            blueMax = 14,
            greenMax = 13,
            games = games
        )
        //check the resulting possible games
        assertEquals(3, possibleGames.size)
        assertEquals(1, possibleGames[0].id)
        assertEquals(2, possibleGames[1].id)
        assertEquals(5, possibleGames[2].id)
    }
}