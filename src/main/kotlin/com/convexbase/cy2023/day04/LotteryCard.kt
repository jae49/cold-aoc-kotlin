package com.convexbase.cy2023.day04

import kotlin.math.pow

data class LotteryCard(
    val id: Int,
    val winningNumbers: List<Int>,
    val ownedNumbers: List<Int>
) {
    var quantity: Int = 1
    val pointValue: Int
        get() {
            val winningSet = winningNumbers.toSet()
            return 2.0.pow(ownedNumbers
                .filter{ it in winningSet }
                .size-1)
                .toInt()
        }
    val matchCount: Int
        get() {
            val winningSet = winningNumbers.toSet()
            return ownedNumbers
                .filter { it in winningSet}
                .size
        }
}
