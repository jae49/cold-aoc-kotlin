package com.convexbase.cy2024.day04

import com.convexbase.CoreUtils

class SolutionTools {

    companion object {

        fun getWordSearch(rsrc: String): WordSearch {
            val lines = CoreUtils.readLinesFromResource(rsrc)
            val width = lines[0].trim().length
            val height = lines.size

            val wordSearch = WordSearch(height, width)
            for ((y, line) in lines.withIndex()) {
                for ((x, c) in line.toCharArray().withIndex()) { wordSearch.setChar(x, y, c) }
            }
            return wordSearch
        }


    }
}