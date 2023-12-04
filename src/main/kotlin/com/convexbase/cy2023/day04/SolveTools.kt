package com.convexbase.cy2023.day04

class SolveTools {

    companion object {
        fun parseLotteryCardLine(line: String):LotteryCard {
            val majorParts = line.trim().split(":")
            val cardText = majorParts[0].trim()
            val numbersText = majorParts[1].trim()
            val cardId = cardText.substring("Card ".length).trim().toInt()

            val numbersParts = numbersText.split("|")
            val winningNumbersText = numbersParts[0].trim()
            val ownedNumbersText = numbersParts[1].trim()
            val winningNumbers = winningNumbersText
                .split(" ")
                .filter{it.isNotEmpty() }
                .map{it.trim().toInt()}
            val ownedNumbers = ownedNumbersText
                .split(" ")
                .filter{it.isNotEmpty()}
                .map {it.trim().toInt()}
            return LotteryCard(
                id = cardId,
                winningNumbers = winningNumbers,
                ownedNumbers = ownedNumbers
            )
        }

        fun parseAllLotteryCards(lines: List<String>):List<LotteryCard> {
            return lines.filter { it.trim().isNotEmpty() }.map { parseLotteryCardLine(it) }
        }

        fun part2LotteryCardsCount(lotteryCards: List<LotteryCard>): Int {
            val maxCardId = lotteryCards.maxOf { it.id }
            for (card in lotteryCards) {
                print(".")
                for (iCard in 1..card.quantity) {
                    for (nextId in 1..card.matchCount) lotteryCards[card.id-1 + nextId].quantity += 1
                }
            }
            println()

            return lotteryCards.sumOf { it.quantity }
        }

    }
}