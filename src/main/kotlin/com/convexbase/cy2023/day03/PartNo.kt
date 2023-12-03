package com.convexbase.cy2023.day03

data class PartNo(
    var lineNo: Int,
    val partStr: String,
    val startIndex: Int,
    val endIndex: Int
) {
    val partNo: Int = partStr.toInt()
}
