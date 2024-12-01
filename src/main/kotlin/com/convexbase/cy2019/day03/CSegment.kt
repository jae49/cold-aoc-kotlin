package com.convexbase.cy2019.day03

data class CSegment(
    val startLength: Long,
    val startX: Long,
    val startY: Long,
    val cDirection: CDirection,
    val cLength: Long
) {
    val endX: Long = when (cDirection) {
        CDirection.Right -> startX + cLength
        CDirection.Left -> startX - cLength
        else -> startX
    }
    val endY: Long = when (cDirection) {
        CDirection.Up -> startY + cLength
        CDirection.Down -> startY - cLength
        else -> startY
    }
}
