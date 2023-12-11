package com.convexbase.cy2023.day11

data class GMap(
    val galaxies: List<Galaxy>,
    var initialWidth:Long,
    var initialHeight:Long
) {
    var width = initialWidth
    var height = initialHeight
    val gPairs:List<Pair<Int,Int>>
        get() {
            val pairs = mutableListOf<Pair<Int,Int>>()
            var count = 2
            val maxCount = galaxies.size
            for (first in 1.rangeTo(maxCount)) {
                for (second in count.rangeTo(maxCount)) {
                    pairs.add(Pair(first, second))
                }
                count += 1
            }
            return pairs
        }
    val allDistances: List<Long>
        get() {
            val list = mutableListOf<Long>()
            var count = 2
            val maxCount = galaxies.size
            for (first in 1.rangeTo(maxCount)) {
                for (second in count.rangeTo(maxCount)) {
                    val firstGalaxy = idMap[first]?:throw IllegalStateException("No such galaxy $first")
                    val secondGalaxy = idMap[second]?:throw IllegalStateException("No such galaxy $second")

                    list.add(Galaxy.distanceBetween(firstGalaxy,secondGalaxy).toLong())
                }
                count += 1
            }
            return list
        }


    var gMap = galaxies.associateBy{ it.gcode }
    val idMap = galaxies.associateBy { it.id }

    fun getG(x: Long, y:Long) = gMap.get(Galaxy.getGCode(x,y))

    fun rowIsEmpty(y: Long):Boolean {
        for (x in 0.rangeUntil(width)) if (getG(x,y) != null) return false
        return true
    }

    fun colIsEmpty(x: Long):Boolean {
        for (y in 0.rangeUntil(height)) if (getG(x,y) != null) return false
        return true
    }

    fun getEmptyRows():List<Long> {
        val emptyRows = mutableListOf<Long>()
        for (y in 0.rangeUntil(height)) {
            if (rowIsEmpty(y)) {
                emptyRows.add(y)
            }
        }
        return emptyRows
    }

    fun getEmptyCols():List<Long> {
        val emptyCols = mutableListOf<Long>()
        for (x in 0.rangeUntil(width)) {
            if (colIsEmpty(x)) {
                emptyCols.add(x)
            }
        }
        return emptyCols
    }

    fun expand(olderAmount: Long = 1L) {
        val emptyCols = getEmptyCols()
        val emptyRows = getEmptyRows()
        var xIncrease = 0L
        var yIncrease = 0L
        emptyCols.forEach { col ->
            shiftGalaxiesRight(col+xIncrease, olderAmount)
            xIncrease += olderAmount
        }
        emptyRows.forEach { row ->
            shiftGalaxiesDown(row + yIncrease, olderAmount)
            yIncrease += olderAmount
        }
    }
    fun shiftGalaxiesRight(afterX: Long, amount: Long) {
        galaxies.filter { it.x > afterX }.forEach { it.shiftRight(amount) }
        gMap = galaxies.associateBy { it.gcode }
        width += amount
    }

    fun shiftGalaxiesDown(afterY: Long, amount: Long) {
        galaxies.filter { it.y > afterY }.forEach { it.shiftDown(amount) }
        gMap = galaxies.associateBy { it.gcode }
        height += amount
    }

    override fun toString():String {
        val sb = StringBuffer()
        val width = width
        val height = height
        sb.append(' ')
        for (x in 0.rangeUntil(width)) sb.append(x % 10)
        sb.append('\n')
        for (y in 0.rangeUntil(height)) {
            sb.append(y % 10)
            for (x in 0.rangeUntil(width)) if (getG(x,y) != null) sb.append(Galaxy.galaxyChar) else sb.append('.')
            sb.append('\n')
        }
        return sb.toString()
    }

    fun toStringWithIds(): String {
        val sb = StringBuffer()
        val width = width
        val height = height
        sb.append(' ')
        for (x in 0.rangeUntil(width)) sb.append(x % 10)
        sb.append('\n')
        for (y in 0.rangeUntil(height)) {
            sb.append(y % 10)
            for (x in 0.rangeUntil(width)) {
                val g = getG(x,y)
                if (g != null) sb.append(g.id % 10) else sb.append('.')
            }
            sb.append('\n')
        }
        return sb.toString()

    }

    fun toStringWithEmpties():String {
        val emptyCols = getEmptyCols()
        val emptyRows = getEmptyRows()
        val sb = StringBuffer()
        val width = width
        val height = height
        sb.append(' ')
        for (x in 0.rangeUntil(width)) sb.append(x % 10)
        sb.append('\n')
        for (y in 0.rangeUntil(height)) {
            sb.append(y % 10)
            for (x in 0.rangeUntil(width)) if (getG(x,y) != null) sb.append(Galaxy.galaxyChar) else {
                if (x in emptyCols) sb.append('V') else
                    if (y in emptyRows) sb.append('>') else
                        sb.append('.')
            }
            sb.append('\n')
        }
        return sb.toString()
    }

    companion object {
        fun getGMap(input: List<String>): GMap {
            var gIndex = 1
            val initialWidth = input[0].length
            val initialHeight = input.size
            val galaxies = mutableListOf<Galaxy>()
            for ((y,line) in input.withIndex()) {
                for ((x,c) in line.toCharArray().withIndex()) {
                    if (c == Galaxy.galaxyChar) galaxies.add(
                        Galaxy(
                            id = gIndex++,
                            x = x.toLong(),
                            y = y.toLong()
                        )
                    )
                }
            }
            return GMap(
                galaxies = galaxies,
                initialWidth = initialWidth.toLong(),
                initialHeight = initialHeight.toLong()
            )
        }
    }

}
