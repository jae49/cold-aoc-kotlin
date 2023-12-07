package com.convexbase.cy2023.day06

data class Race(
    val raceNum: Int,
    val raceTimeInMs: Long,
    val recordDistance: Long
) {

    fun calcDistanceInMm(pressTimeInMs: Long):Long {
        val velocityInMmPerMs = pressTimeInMs * 1 // the 1 stands for 1 millimeter /per millisecond
        return ( velocityInMmPerMs * (raceTimeInMs - pressTimeInMs))
    }
}
