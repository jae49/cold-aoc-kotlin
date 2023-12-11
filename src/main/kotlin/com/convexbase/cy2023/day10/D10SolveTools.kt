package com.convexbase.cy2023.day10


class D10SolveTools {

    companion object {


        //returns top, bottom, left and right sketchpoint neighbors in a list in that order
        private fun getConnections(point: SketchPoint, rawMap: Map<String, SketchPoint>): List<SketchPoint?> {
            val top = rawMap[point.topNeighborCode]
            val bottom = rawMap[point.bottomNeighborCode]
            val left = rawMap[point.leftNeighborCode]
            val right = rawMap[point.rightNeighborCode]
            val validTop = (top?.spType?:SPType.GROUND) in SketchPoint.validTopConnectionTypes
            val validBottom: Boolean = (bottom?.spType ?: SPType.GROUND) in SketchPoint.validBottomConnectionTypes
            val validLeft: Boolean = (left?.spType ?: SPType.GROUND) in SketchPoint.validLeftConnectionTypes
            val validRight: Boolean = (right?.spType ?: SPType.GROUND) in SketchPoint.validRightConnectionTypes
            val topCon = if (validTop) top else null
            val bottomCon = if (validBottom) bottom else null
            val leftCon = if (validLeft) left else null
            val rightCon = if (validRight) right else null
            when (point.spType) {
                SPType.START -> return listOf(topCon, bottomCon, leftCon, rightCon)
                SPType.VPIPE -> return listOf(topCon, bottomCon, null, null)
                SPType.HPIPE -> return listOf(null, null, leftCon, rightCon)
                SPType.BEND_UP_LEFT -> return listOf(null, bottomCon, leftCon, null)
                SPType.BEND_UP_RIGHT -> return listOf(null, bottomCon, null, rightCon)
                SPType.BEND_DOWN_LEFT -> return listOf(topCon, null, leftCon, null)
                SPType.BEND_DOWN_RIGHT -> return listOf(topCon, null, null, rightCon)
                else -> return listOf(null, null, null, null)
            }
        }

        fun getSketch(input: List<String>):Sketch {
            val noBlankLinesInput = input.filter { it.isNotBlank()}
            val points = mutableListOf<SketchPoint>()
            // go through the entire sketch and extract all the valid points in it
            for ((y, row) in noBlankLinesInput.withIndex()) {
                for ((x,colChar) in row.toCharArray().withIndex()) {
                    val spType = SPType.entries.find { it.c == colChar }?:continue
                    points.add(SketchPoint(spType, x, y))
                }
            }

            val rawMap = points.associateBy{ it.indexCode }
            // create sketchpoints with neighbors filled out
            points.forEach { sketchPoint ->
                val (top, bottom, left, right) = getConnections(sketchPoint, rawMap)
                sketchPoint.topConnection = top
                sketchPoint.bottomConnection = bottom
                sketchPoint.rightConnection = right
                sketchPoint.leftConnection = left
            }
            return Sketch(
                width = noBlankLinesInput[0].trim().length,
                height = noBlankLinesInput.size,
                points = points
            )
        }

    }
}