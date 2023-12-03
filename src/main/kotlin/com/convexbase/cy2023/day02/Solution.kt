package com.convexbase.cy2023.day02

class Solution {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val gameText = Solution::class.java.getResource("/data/day02/input.txt").readText()
            val games = SolveTools.loadGames(gameText)


            val possibleGames = SolveTools.possibleGamesWithMaxColors(
                redMax = 12,
                blueMax = 14,
                greenMax = 13,
                games
            )
            var idSum = 0
            possibleGames.forEach() { game -> idSum += game.id}

            var totalMaxCubeSum = 0
            games.forEach() { game -> totalMaxCubeSum += game.maxCube}
            println("Total Games: ${games.size}")
            println("Total MaxCube Sum: $totalMaxCubeSum")
            println("Possible Game Quantity: ${possibleGames.size}")
            println("Possible Games ID Sum: $idSum")

        }
    }

}