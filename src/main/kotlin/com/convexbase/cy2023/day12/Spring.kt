package com.convexbase.cy2023.day12

enum class Spring(val c: Char) {
    Operational('.'),
    Damaged('#');

    companion object {
        val possibleChars = entries.map { it.c }
    }
}