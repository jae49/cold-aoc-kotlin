package com.convexbase.cy2023.day06

class SolveTools {

    companion object {
        fun parseForPart1(lines: List<String>):List<Race> {
            val inputData =  lines
                .filter{it.isNotBlank()}
                .map { line ->
                    line.substringAfter(':')
                        .trim()
                        .split(' ')
                        .filter { it.isNotBlank() }
                        .map{ it.toInt() }
                }
            val races = mutableListOf<Race>()
            check(inputData.size == 2)
            for (raceNum in 0..<inputData[0].size) races.add(Race(
                raceNum = raceNum,
                raceTimeInMs = inputData[0][raceNum].toLong(),
                recordDistance = inputData[1][raceNum].toLong()
            ))
            return races
        }

        fun parseForPart2(lines:List<String>):Race {
            val inputData = lines.map {
                it.substringAfter(':').replace(" ", "").toLong()
            }
            return Race(
                raceNum = 0,
                raceTimeInMs = inputData[0],
                recordDistance = inputData[1]
            )
        }

        fun getWinningDistanceCounts(races:List<Race>):List<Long> {
            return races.map {race ->
                (0..race.raceTimeInMs).fold(0L) { count, pressTimeInMs ->
                    if (race.calcDistanceInMm(pressTimeInMs) > race.recordDistance) count + 1
                    else count
                }
            }
        }

        fun getWinningRaceCountComputation(winningDistanceCounts: List<Long>):Long {
            if (winningDistanceCounts.isEmpty()) return 0
            var winComputation = 1L
            winningDistanceCounts.forEach { winComputation *= it}
            return winComputation
        }

    }
}