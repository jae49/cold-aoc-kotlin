package com.convexbase.cy2023.day02

data class Game(
    val id: Int,
    val grabs:List<GameGrab>
) {
    val maxGrab: GameGrab
        get() {
            var redMax: Int = 0
            var greenMax: Int = 0
            var blueMax: Int = 0
            grabs.forEach { grab ->
                if (grab.red > redMax) redMax = grab.red
                if (grab.blue > blueMax) blueMax = grab.blue
                if (grab.green > greenMax) greenMax = grab.green
            }
            return GameGrab(
                red = redMax,
                blue = blueMax,
                green = greenMax
            )
        }
    val maxCube: Int
        get() {
            return maxGrab.red * maxGrab.blue * maxGrab.green
        }
}
