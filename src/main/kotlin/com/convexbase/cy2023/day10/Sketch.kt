package com.convexbase.cy2023.day10

data class Sketch(
    val width: Int,
    val height: Int,
    val points: List<SketchPoint>
) {
    val pointsMap = points.associateBy {it.indexCode}
    val startPoints = points.filter { it.spType == SPType.START }
    fun getPoint(x: Int, y: Int) = pointsMap[SketchPoint.getIndexCode(x, y)]
    fun calculateDistancesFromStart() {
        val pointsToCalculate = mutableListOf<Pair<SketchPoint,SketchPoint>>()
        startPoints.forEach { startpoint ->
            if (startpoint.allConnections.size != 2) throw IllegalStateException("Startpoint " +
                "(${startpoint.xPosition},${startpoint.yPosition}). " +
                "Improper Connection count of ${startpoint.allConnections.size}")
            startpoint.allConnections.forEach { pointsToCalculate.add(Pair(startpoint, it))}
            startpoint.distanceFromStart = 0

            while (pointsToCalculate.isNotEmpty()) {
                val (lastPoint, nextPoint) = pointsToCalculate.removeFirst()
                nextPoint.distanceFromStart = lastPoint.distanceFromStart + 1
                if (nextPoint.distanceFromStart > startpoint.maxDistance)
                    startpoint.maxDistance = nextPoint.distanceFromStart
                val connections = nextPoint.allConnections
                if (connections.size != 2) throw IllegalStateException("(${nextPoint.xPosition}," +
                    "${nextPoint.yPosition}). Improper Connection count of ${connections.size}")
                val otherConnection = if (connections[0] === lastPoint) connections[1] else connections[0]
                if (otherConnection.distanceFromStart < 0) pointsToCalculate.add(Pair(nextPoint,otherConnection))
            }
        }
    }

    fun getPureSketch():Sketch {
        calculateDistancesFromStart()
        return Sketch(
            width = width,
            height = height,
            points = points.map {point ->
                if (point.distanceFromStart < 0) SketchPoint(
                    spType = SPType.GROUND,
                    xPosition = point.xPosition,
                    yPosition = point.yPosition
                ) else point
            }
        )
    }


    fun markVisibilityUsingPipes() {
        for (y in 0.rangeUntil(height)) {
            var crossedVertical = 0
            for (x in 0.rangeUntil(width)) {
                val point = getPoint(x, y)?:continue
                val pipe = point.spType != SPType.GROUND && point.spType != SPType.HPIPE &&
                    point.spType != SPType.BEND_DOWN_LEFT && point.spType != SPType.BEND_DOWN_RIGHT
                if (pipe) {
                    crossedVertical += 1
                    point.visibility = VType.EDGE
                } else {
                    if (point.spType == SPType.GROUND) {
                        if (crossedVertical % 2 == 1) point.visibility = VType.INNER
                        else point.visibility = VType.OUTER
                    }
                }
            }
        }
    }

    override fun toString():String {
        val sb = StringBuffer()
        sb.append(' ')
        for (x in 0.rangeUntil(width))sb.append(x % 10)
        sb.append('\n')
        for (y in 0.rangeUntil(height)) {
            sb.append(y % 10)
            for (x in 0.rangeUntil(width)) {
                val point = getPoint(x, y)
                if (point == null) sb.append('!') else {
                    val pv = point.visibility
                    val c = when (pv) {
                        VType.OUTER -> 'O'
                        VType.EDGE -> point.spType.bc
                        VType.INNER -> 'I'
                        VType.UNKNOWN -> point.spType.bc
                    }
                    sb.append(c)
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    fun toCountString():String {
        val sb = StringBuffer()
        sb.append(' ')
        for (x in 0.rangeUntil(width))sb.append(x % 10)
        sb.append('\n')
        for (y in 0.rangeUntil(height)) {
            sb.append(y % 10)
            for (x in 0.rangeUntil(width)) {
                val point = getPoint(x, y)
                if (point == null) sb.append('!')
                else {
                    if (point.distanceFromStart == -1) sb.append('.')
                    else sb.append(point.distanceFromStart % 10)
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    fun toOriginalString():String {
        val sb = StringBuffer()
        sb.append(' ')
        for (x in 0.rangeUntil(width))sb.append(x % 10)
        sb.append('\n')
        for (y in 0.rangeUntil(height)) {
            sb.append(y % 10)
            for (x in 0.rangeUntil(width)) {
                val point = getPoint(x, y)
                if (point == null) sb.append('!') else sb.append(point.spType.c)
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    fun toValidPointsString():String {
        val sb = StringBuffer()
        for (y in 0.rangeUntil(height)) {
            for (x in 0.rangeUntil(width)) {
                val point = getPoint(x, y)
                if (point == null) sb.append('!') else {
                    if (point.noConnections) sb.append('.')
                    else sb.append(point.spType.bc)
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }
}