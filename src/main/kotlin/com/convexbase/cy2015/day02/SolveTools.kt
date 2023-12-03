package com.convexbase.cy2015.day02

class SolveTools {

    companion object {
        fun getWrappingSquareFeet(length: Int, width: Int, height: Int): Int {
            val sidesSqFt = mutableListOf<Int>()
            sidesSqFt.add(length*width)
            sidesSqFt.add(width*height)
            sidesSqFt.add(height*length)
            sidesSqFt.sort()
            return sidesSqFt[0]*3 + sidesSqFt[1]*2 + sidesSqFt[2]*2
        }

        //defined as the perimeter of the shortest side + cubic feet of volume
        fun getRibbonLength(length: Int, width: Int, height: Int): Int {
            val sides = mutableListOf<Int>()
            sides.add(length)
            sides.add(width)
            sides.add(height)
            sides.sort()
            return sides[0]*2 + sides[1]*2 + length * width * height
        }

        fun getLengthWidthHeight(line:String):Triple<Int, Int, Int> {
            val dimensions = line.trim().split("x")
            return Triple(dimensions[0].toInt(), dimensions[1].toInt(), dimensions[2].toInt())
        }

        fun getBoxDimensions(lines: List<String>):List<Triple<Int, Int, Int>> {
            val boxes = mutableListOf<Triple<Int, Int, Int>>()
            for (line in lines) if (line.isNotEmpty()) boxes.add(getLengthWidthHeight(line))
            return boxes
        }
    }
}