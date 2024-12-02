package com.convexbase.cy2024.day02

import com.convexbase.CoreUtils
import kotlin.math.abs

class SolutionTools {

    enum class LevelType {
        SAFE_INCREASING,
        SAFE_DECREASING,
        UNSAFE
    }

    companion object {

        fun checkLevel(first: Long, second: Long):LevelType {
            val diff = second - first
            val absDiff = abs(diff)
            if (absDiff < 1 || absDiff > 3) return LevelType.UNSAFE
            if (diff < 0) return LevelType.SAFE_DECREASING
            return LevelType.SAFE_INCREASING
        }

        fun levelsSafety(levels: List<Long>):Pair<LevelType,Int> {
            var lastLevel = levels[1]
            var lastLevelType = checkLevel(levels[0], levels[1])
            if (lastLevelType == LevelType.UNSAFE) { return Pair(LevelType.UNSAFE, 1) }
            for (i in 2 until levels.size) {
                val levelType =checkLevel(lastLevel, levels[i])
                if (levelType != lastLevelType) lastLevelType = LevelType.UNSAFE
                if (lastLevelType == LevelType.UNSAFE) return Pair(LevelType.UNSAFE, i)
                lastLevel = levels[i]
            }
            return Pair(lastLevelType, levels.size-1)
        }

        fun checkLevels(lines: List<String>):Long {
            var safelines = 0L
            var unsafelines = 0L
            for (line in lines) {
                val levels = CoreUtils.getLineLongs(line)
                val (lastLevelType, _) = levelsSafety(levels)
                if (lastLevelType != LevelType.UNSAFE) safelines++
                else unsafelines++
            }
            println("SafeLines: $safelines, UnsafeLines: $unsafelines, Total: ${safelines + unsafelines}")
            return safelines
        }

        fun checkedDampedLevels(lines: List<String>):Long {
            var safelines = 0L
            var unsafelines = 0L
            var lineno = 1
            for (line in lines) {
                val levels = CoreUtils.getLineLongs(line)
                val (lastLevelType, index) = levelsSafety(levels)
                if (lastLevelType != LevelType.UNSAFE) safelines++
                else {
                    var saved = false
                    print("Line #$lineno: Unsafe at element $index = ${levels[index]}")
                    val variant1 = mutableListOf<Long>().apply {
                        addAll(levels.subList(0, index))
                        addAll(levels.subList(index + 1, levels.size))
                    }
                    val variant2 = mutableListOf<Long>().apply {
                        addAll(levels.subList(0, index -1))
                        addAll(levels.subList(index, levels.size))
                    }
                    if (index == 2) {
                        val variant3 = mutableListOf<Long>().apply {
                            addAll(levels.subList(1, levels.size))
                        }
                        val (variant3LevelType, _) = levelsSafety(variant3)
                        if (variant3LevelType != LevelType.UNSAFE) {
                            print("; Safely removed element 0 = ${levels[0]}")
                            saved = true
                        }
                    }
                    val (variant1LevelType, _) = levelsSafety(variant1)
                    if (variant1LevelType != LevelType.UNSAFE) {
                        print("; Safely removed element $index = ${levels[index]}")
                        saved = true
                    }
                    val (variant2LevelType, _) = levelsSafety(variant2)
                    if (variant2LevelType != LevelType.UNSAFE) {
                        print("; Safely removed element ${index-1} = ${levels[index - 1]}")
                        saved = true
                    }
                    if (saved) print("; Saved")
                    println()
                    if (saved) safelines++
                    else unsafelines++
                }
                lineno++
            }

            println("SafeLines: $safelines, UnsafeLines: $unsafelines, Total: ${safelines + unsafelines}")
            return safelines

        }

    }
}