package com.convexbase.cy2019.day03

data class CWire(
    val startX: Long,
    val startY: Long,
    val segments: List<CSegment>
) {
    val horizontalSegments = segments
        .filter { it.cDirection == CDirection.Left || it.cDirection == CDirection.Right }
    val verticalSegments = segments
        .filter { it.cDirection == CDirection.Up || it.cDirection == CDirection.Down }
    val length = segments.sumOf { it.cLength }
}
