package com.convexbase.cy2023.day07

data class CamelCardBid(
    val id: Int,
    val hand: String,
    val bid: Int,
    val jokersWild: Boolean = false
):Comparable<CamelCardBid> {
    val version = 16
    val cards = hand.toCharArray().asList()
    val subsitutedCards = cards.map { c ->
        when (c) {
            'T' -> 'A'
            'J' -> 'B'
            'Q' -> 'C'
            'K' -> 'D'
            'A' -> 'E'
            in '2'..'9' -> c
            else -> throw IllegalArgumentException ("Unknown Card '$c'")
        }
    }
    val orderedCards = subsitutedCards.sortedDescending()
    val multipleStrength = orderedCards.groupBy { it }.toList().map {Pair(it.second.size, it.first) }
        .sortedByDescending { it.first }
    val handStrength = getStrength()
    override fun compareTo(other:CamelCardBid):Int {
        if (handStrength > other.handStrength) return 1
        if (handStrength < other.handStrength) return -1
        //equal handstrength, compare character by character now
        for (i in 0..<subsitutedCards.size) {
            val myCard = if (jokersWild && subsitutedCards[i] == 'B') '0' else subsitutedCards[i]
            val theirCard = if (jokersWild && other.subsitutedCards[i] == 'B') '0' else other.subsitutedCards[i]
            if (myCard > theirCard) return 1
            if (myCard < theirCard) return -1
        }
        return 0
    }
    fun getStrength(): Int {
        val optionalStrength = if (jokersWild) getJokersWildMultipleStrength() else multipleStrength
        var fullHousePossible = false
        var pairPossible = false
        for (strength in optionalStrength) {
            when (strength.first) {
                5 -> return 6
                4 -> return 5
                3 -> fullHousePossible = true
                2 -> {
                    if (pairPossible) return 2
                    if (fullHousePossible) return 4
                    else pairPossible = true
                }
                1 -> {
                    if (pairPossible) return 1
                    if (fullHousePossible) return 3 else return 0
                }
            }
        }
        if (pairPossible) return 2
        if (fullHousePossible) return 3
        return 0
    }

    fun getJokersWildMultipleStrength():List<Pair<Int, Char>> {
        var jokers: Pair<Int,Char>? = null

        val noJokersStrength = mutableListOf<Pair<Int, Char>>()
        for (strength in multipleStrength) {
            if (strength.second == 'B') {  // joker is represented by a B
                jokers = strength
            } else {
                noJokersStrength.add(strength)
            }
        }
        if (noJokersStrength.isEmpty()) {
            // special case of all jokers
            return multipleStrength
        }
        if (jokers == null) return multipleStrength
        // return a new strength since we have jokers
        val jokerStrength = noJokersStrength[0].first
        val pairsOfJokerStrength = noJokersStrength.filter { it.first == jokerStrength}.count()
        return noJokersStrength.mapIndexed{index, strength ->
            if (index == pairsOfJokerStrength-1) Pair(strength.first+jokers.first, strength.second)

            else strength
        }.sortedByDescending{ it.first }
    }

    companion object {
        fun getCamelCardBidList(input: List<String>, jokersWild:Boolean = false) = input.mapIndexed { index, cardsBidLine ->
            val (hand, bid) = cardsBidLine.split(" ")
            CamelCardBid(index, hand, bid.toInt(), jokersWild)
        }

        fun getTotalBid(sortedBids: List<CamelCardBid>): Int {
            var sortedRank = 1
            return sortedBids.sumOf { sortedRank++ * it.bid}
        }
    }
}
