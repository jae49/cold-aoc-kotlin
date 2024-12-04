package com.convexbase.cy2024.day04

typealias Position = Pair<Int,Int>

fun Position.code():Long {
    return first * 10000L + second
}

class WordSearch(
    val width: Int,
    val height: Int
) {
    val data = CharArray(width * height)
    data class Found(
        val charPositions: List<Position>,
    ) {
        val secondCode: Long
            get() {
                return charPositions[1].code().toLong()
            }
    }

    fun getChar(x: Int, y: Int) = data[y * width + x]
    fun setChar(x: Int, y: Int, char: Char) { data[y * width + x] = char }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("  ")
        for (x in 0 until width) {
            sb.append(x % 10)
        }
        sb.append('\n')
        for (y in 0 until height) {
            sb.append(y % 10)
            sb.append(' ')
            for (x in 0 until width) {sb.append(getChar(x,y))}
            sb.append('\n')
        }
        return sb.toString()
    }

    fun search(word:String): List<Found> {
        val foundList = mutableListOf<Found>()
        val wordChars = word.toCharArray()
        val startChar = wordChars[0]
        val wordLength = wordChars.size
        for (y in 0 until height) {
            for (x in 0 until width ) {
                val c = getChar(x,y)
                if (c == startChar) {
                    foundList.addAll(radialSearch(x, y, wordChars))
                }
            }
        }
        return foundList
    }

    fun searchDiagonals(word: String): List<Found> {
        val foundList = mutableListOf<Found>()
        val wordChars = word.toCharArray()
        val startChar = wordChars[0]
        val wordLength = wordChars.size
        for (y in 0 until height) {
            for (x in 0 until width ) {
                val c = getChar(x,y)
                if (c == startChar) {
                    foundList.addAll(diagonalSearch(x, y, wordChars))
                }
            }
        }
        return foundList
    }

    fun searchXMASShape(): List<Found> {
        val shapeList = mutableListOf<Found>()
        val possible = searchDiagonals("MAS")
        val pMapA = mutableMapOf<Long, Found>()
        for (entry in possible) {
            if (pMapA.containsKey(entry.secondCode)) shapeList.add(entry)
            else pMapA[entry.secondCode] = entry
        }
        return shapeList
    }

    fun checkPositions(found: Found, wordchars: CharArray): Boolean {
        for ((index, position) in found.charPositions.withIndex()) {
            val x = position.first
            val y = position.second
            if (x < 0 || x > width -1) return false
            if (y < 0 || y > height -1) return false
            if (getChar(position.first, position.second) != wordchars[index]) return false
        }
        return true
    }

    fun getUpPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x, y + i))
        return result
    }

    fun getDownPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x, y - i))
        return result
    }

    fun getLeftPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x-i, y))
        return result
    }

    fun getRightPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x+i, y))
        return result
    }

    fun getUpLeftPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x-i, y + i))
        return result
    }

    fun getDownLeftPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x-i, y - i))
        return result
    }

    fun getUpRightPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x+i, y-i))
        return result
    }

    fun getDownRightPositions(x: Int, y: Int, length: Int): List<Position> {
        val result = mutableListOf<Position>()
        for (i in 0 until length) result.add(Position(x+i, y+i))
        return result
    }



    fun generateRadialFound(startX: Int, startY: Int, wordchars: CharArray): List<Found> {
        // 0,0 is top left
        val foundMaybe = mutableListOf<Found>()
        val wlength = wordchars.size
        foundMaybe.add(Found(getUpPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getDownPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getLeftPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getRightPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getUpLeftPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getDownLeftPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getUpRightPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getDownRightPositions(startX, startY, wlength)))
        return foundMaybe
    }

    fun generateDiagonalFound(startX: Int, startY: Int, wordchars: CharArray): List<Found> {
        val foundMaybe = mutableListOf<Found>()
        val wlength = wordchars.size
        foundMaybe.add(Found(getUpLeftPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getDownLeftPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getUpRightPositions(startX, startY, wlength)))
        foundMaybe.add(Found(getDownRightPositions(startX, startY, wlength)))
        return foundMaybe
    }

    fun radialSearch(posX : Int, posY: Int, wordchars: CharArray): List<Found> {
        val reallyFound = mutableListOf<Found>()
        for (maybe in generateRadialFound(posX, posY, wordchars)) {
            if (checkPositions(maybe, wordchars)) { reallyFound.add(maybe)}
        }
        return reallyFound
    }

    fun diagonalSearch(posX: Int, posY: Int, wordchars: CharArray): List<Found> {
        val reallyFound = mutableListOf<Found>()
        for (maybe in generateDiagonalFound(posX, posY, wordchars)) {
            if (checkPositions(maybe, wordchars)) { reallyFound.add(maybe)}
        }
        return reallyFound
    }
}