package com.convexbase.cy2023.day04

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lines = Solution::class.java.getResource("/data/2023/day04/input.txt").readText().lines()
            val lotteryCards = SolveTools.parseAllLotteryCards(lines)
            val totalPoints = lotteryCards.sumOf { it.pointValue }
            val cardCount = SolveTools.part2LotteryCardsCount(lotteryCards)
            println("The total points for the lottery cards is $totalPoints")
            println("The total number of cards for part 2 is $cardCount")
        }
    }
}