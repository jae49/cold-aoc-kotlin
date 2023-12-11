package com.convexbase.cy2023.day10

data class SketchPoint(
    val spType: SPType,
    val xPosition: Int,
    val yPosition: Int,
) {
    var leftConnection: SketchPoint? = null
    var rightConnection: SketchPoint? = null
    var topConnection: SketchPoint? = null
    var bottomConnection: SketchPoint? = null
    var visibility = VType.UNKNOWN
    val isABend:Boolean = spType == SPType.BEND_UP_LEFT || spType == SPType.BEND_UP_RIGHT
        || spType == SPType.BEND_DOWN_LEFT || spType == SPType.BEND_DOWN_RIGHT
    val allConnections:List<SketchPoint>
        get() {
            return listOf(leftConnection, rightConnection, topConnection,bottomConnection).filterNotNull()
        }
    var distanceFromStart = -1
    var maxDistance = -1
    val indexCode = getIndexCode(xPosition,yPosition)
    val leftNeighborCode = getIndexCode(xPosition - 1, yPosition)
    val rightNeighborCode = getIndexCode(xPosition + 1, yPosition)
    val topNeighborCode = getIndexCode(xPosition, yPosition - 1)
    val bottomNeighborCode = getIndexCode(xPosition, yPosition + 1)
    val noConnections:Boolean = allConnections.all { it == null }
    override fun toString():String {
        val sb = StringBuffer()
        sb.append("Position: ($xPosition, $yPosition)\n")
        sb.append("Connections View:\n\t\u250F\u2501\u2501\u2501\u2513\n\t\u2503.")
        sb.append(topConnection?.spType?.bc ?: '.')
        sb.append(".\u2503\n\t\u2503")
        sb.append(leftConnection?.spType?.bc ?: '.')
        sb.append(spType.bc)
        sb.append(rightConnection?.spType?.bc ?: '.')
        sb.append("\u2503\n\t\u2503.")
        sb.append(bottomConnection?.spType?.bc ?: '.')
        sb.append(".\u2503\n\t\u2517\u2501\u2501\u2501\u251B")
        return sb.toString()
    }

    fun toOriginalString():String {
        val sb = StringBuffer()
        sb.append("Position: ($xPosition, $yPosition)\n")
        sb.append("Connections View:\n\t\u250F\u2501\u2501\u2501\u2513\n\t\u2503.")
        sb.append(topConnection?.spType?.c ?: '.')
        sb.append(".\u2503\n\t\u2503")
        sb.append(leftConnection?.spType?.c ?: '.')
        sb.append(spType.c)
        sb.append(rightConnection?.spType?.c ?: '.')
        sb.append("\u2503\n\t\u2503.")
        sb.append(bottomConnection?.spType?.c ?: '.')
        sb.append(".\u2503\n\t\u2517\u2501\u2501\u2501\u251B")
        return sb.toString()

    }

    companion object {
        inline fun getIndexCode(x:Int, y:Int):String = "x${x}y{$y}"
        val validTopConnectionTypes = listOf(SPType.START, SPType.VPIPE, SPType.BEND_UP_LEFT, SPType.BEND_UP_RIGHT)
        val validBottomConnectionTypes = listOf(SPType.START, SPType.VPIPE, SPType.BEND_DOWN_LEFT, SPType.BEND_DOWN_RIGHT)
        val validLeftConnectionTypes = listOf(SPType.START, SPType.HPIPE, SPType.BEND_UP_RIGHT, SPType.BEND_DOWN_RIGHT)
        val validRightConnectionTypes = listOf(SPType.START, SPType.HPIPE, SPType.BEND_UP_LEFT, SPType.BEND_DOWN_LEFT)
    }
}