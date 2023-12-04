package com.convexbase.cy2023.day04

import kotlin.test.Test
import kotlin.test.assertEquals


class SolveToolsTests {

    @Test
    fun testParseLotteryCardLine() {
        val lines = SolveToolsTests::class.java.getResource("/data/2023/day04/testinput01.txt").readText().lines()
        assertEquals(6, lines.size)
        val lotteryCard = SolveTools.parseLotteryCardLine(lines[0])
        assertEquals(1, lotteryCard.id)
        assertEquals(5, lotteryCard.winningNumbers.size)
        assertEquals(8, lotteryCard.ownedNumbers.size)
        assertEquals(8, lotteryCard.pointValue)
    }

    fun testPart2LotteryCardsCount() {
        val lines = SolveToolsTests::class.java.getResource("/data/2023/day04/testinput01.txt").readText().lines()
        assertEquals(6, lines.size)
        val cards = SolveTools.parseAllLotteryCards(lines)
        val cardCount = SolveTools.part2LotteryCardsCount(cards)
        assertEquals(30, cardCount)
    }
}