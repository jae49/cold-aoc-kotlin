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

        fun levelsSafety(levels: List<Long>):LevelType {
            var lastLevel = levels[1]
            var lastLevelType = checkLevel(levels[0], levels[1])
            for (i in 2 until levels.size) {
                val levelType =checkLevel(lastLevel, levels[i])
                if (levelType != lastLevelType) lastLevelType = LevelType.UNSAFE
                if (lastLevelType == LevelType.UNSAFE) break
                lastLevel = levels[i]
            }
            return lastLevelType
        }

        fun checkLevels(lines: List<String>):Long {
            var safelines = 0L
            for (line in lines) {
                val levels = CoreUtils.getLineLongs(line)
                val lastLevelType = levelsSafety(levels)
                if (lastLevelType != LevelType.UNSAFE) safelines++
            }
            return safelines
        }

    }
}