package com.convexbase.cy2023.day09

class SolveTools {

    companion object {

        fun getReadingsList(readingsLines: List<String>) =
            readingsLines.map { line -> line.split(" ").map { it.toLong()}}

        fun subtract(readings: List<Long>): List<Long> {
            var a = readings[0]
            return readings.drop(1).map { b ->
                val result = b -a
                a = b
                result
            }
        }

        fun lineIsZeros(readings: List<Long>):Boolean {
            for (reading in readings) if (reading != 0L) return false
            return true
        }

        fun subtractAndExpand(readings: List<Long>):List<List<Long>> {
            val memory = mutableListOf<List<Long>>(readings)
            while (!lineIsZeros(memory.last())) {
                memory.add(subtract(memory.last()))
            }
            return memory
        }

        fun calculateMissing(expandedLists: List<List<Long>>): List<Long> {
            val missingList = mutableListOf<Long>(0L)
            for (index in 1 ..< expandedLists.size) {
                missingList.add(expandedLists[index].last() + missingList.last())
            }
            return missingList
        }

        fun getNextPredicted(readingsList: List<List<Long>>):List<Long> {
            return readingsList.map { it -> calculateMissing(subtractAndExpand(it).reversed()).last()}
        }


        fun getPart1Answer(readingsLines: List<String>) = getNextPredicted(getReadingsList(readingsLines)).sum()

        fun getPart2Answer(readingsLines: List<String>) =
                               getNextPredicted(getReadingsList(readingsLines).map { it.reversed()}).sum()
    }
}