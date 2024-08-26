package com.convexbase.cy2023.day12

import java.util.*

data class DRecordRow(
    val springs: CharArray,
    val cdsList: List<Int>
) {
    val unknownSprings = springs.withIndex().filter { !(it.value in Spring.possibleChars) }.map { it.index }
    val damaged = cdsList.sum()
    val combinations = sequence {
        val tchars = springs.clone()
        for (i in 0.rangeUntil(D12SolveTools.totalCombinations(2, unknownSprings.size))) {
            val bits = Integer.toBinaryString(i.toInt()).padStart(unknownSprings.size,'0').toCharArray()
            for ((index,loc) in unknownSprings.withIndex()) {
                val newC = if (bits[index] == '0') Spring.possibleChars[0]
                else Spring.possibleChars[1]
                tchars[loc] = newC
            }
            yield(tchars)
        }
    }

    val possibleCombinationsCount = D12SolveTools.totalCombinations(2, unknownSprings.size)

    fun match(pm: CharArray):Boolean {
        if (getDamaged(pm) != damaged) return false
        if (getCDS(pm) == cdsList) return true
        return false
    }

    fun getDamaged(s:CharArray) = s.filter{it == Spring.Damaged.c}.count()

    fun getAllMatches():List<CharArray> {
        val matches: MutableList<CharArray> = mutableListOf()
        for (combination in combinations) {
            if (match(combination)) {
                matches.add(combination.clone())
            }
        }
        return matches
    }

    fun getMatchCount():Long {
        var count: Long = 0L
        for (combination in combinations) {
            if (match(combination)) {
                count++
            }
        }
        return count
    }

    fun getCDS(s: CharArray):List<Int> {
       var inS = 0
       val cdList = mutableListOf<Int>()
       for (c in s) {
           if (c == Spring.Damaged.c) {
               inS += 1
           } else {
               if (inS > 0) cdList.add(inS)
               inS = 0
           }
       }
        if (inS > 0) cdList.add(inS)
       return cdList
    }

    override fun toString(): String {
        return "${String(springs)} ${cdsList.joinToString(",")}"
    }

    companion object {
        fun toDRecordRow(row: String):DRecordRow {
            val springs = row.substringBefore(' ').toCharArray()
            val cdsList = row.substringAfter(' ').split(',').map { it.toInt()}
            return DRecordRow(
                springs = springs,
                cdsList = cdsList
            )
        }

        fun to5XDRecordRow(row: String):DRecordRow {
            val springs = row.substringBefore(' ').toCharArray()
            val fiveSprings = springs + '?' + springs + '?' + springs + '?' + springs + '?' + springs
            val cdsList = row.substringAfter(' ').split(',').map { it.toInt()}
            val fiveCdsList = mutableListOf<Int>().apply {
                addAll(cdsList)
                addAll(cdsList)
                addAll(cdsList)
                addAll(cdsList)
                addAll(cdsList)
            }
            return DRecordRow(
                springs = fiveSprings,
                cdsList = fiveCdsList
            )
        }
    }
}
