package com.convexbase.cy2023.day02

class SolveTools {

    companion object {
        @JvmStatic
        fun toGameGrab(colorSetText: String): GameGrab {
            val setParts = colorSetText.split(",")
            var redCount = 0
            var blueCount = 0
            var greenCount = 0
            for (colorCountText in setParts) {
                val (color, count) = getColorCountPair(colorCountText.trim())
                when (color) {
                    "red" -> if (redCount != 0) throw IllegalArgumentException("Duplicate Red") else redCount = count
                    "blue" -> if (blueCount != 0) throw IllegalArgumentException("Duplicate Blue") else blueCount = count
                    "green" -> if (greenCount != 0) throw IllegalArgumentException("Duplicate Green") else greenCount = count
                    else -> throw IllegalArgumentException("Invalid color: $color")
                }
            }
            return GameGrab(
                red = redCount,
                blue = blueCount,
                green = greenCount
            )
        }

        @JvmStatic
        fun getColorCountPair(colorCountText: String): Pair<String, Int> {
            val parts = colorCountText.split(" ")
            if (parts.size != 2) throw IllegalArgumentException("Invalid Color Count Text: $colorCountText")
            val count = parts[0].trim().toInt()
            val color = parts[1].trim()
            return Pair(color, count)
        }


        @JvmStatic
        fun getGrabs(grabsText: String): List<GameGrab> {
            val grabParts = grabsText.split(";")
            val grabs = mutableListOf<GameGrab>()
            for (grabPart in grabParts) grabs.add(toGameGrab(grabPart.trim()))
            return grabs
        }

        @JvmStatic
        fun getGame(line: String): Game {
            val parts = line.split(":")
            if (parts.size != 2) throw IllegalArgumentException("Invalid Number of line parts: ${parts.size}")
            val gameIdPart = parts[0].trim()
            if (!gameIdPart.startsWith("Game "))
                throw IllegalArgumentException("Invalid Game Line Header: ${gameIdPart}")
            val gameIdStr = gameIdPart.substring("Game ".length)
            val gameId = gameIdStr.toInt()
            return Game(
                id = gameId,
                grabs = getGrabs(parts[1].trim())
            )
        }

        @JvmStatic
        fun loadGames(gamesText: String): List<Game> {
            val games = mutableListOf<Game>()
            var lineCount = 0
            for (line in gamesText.lines()) {
                lineCount += 1
                if (line.isBlank()) continue
                val game = runCatching { getGame(line)}.fold(
                    onSuccess = { it },
                    onFailure = {
                        throw IllegalArgumentException("Failed to load line $lineCount: ${it.message}")
                    }
                )
                games.add(game)
            }
            return games
        }

        fun possibleGamesWithMaxColors(
            redMax: Int,
            blueMax: Int,
            greenMax: Int,
            games: List<Game>
        ): List<Game> {
            val possibleGames = mutableListOf<Game>()
            games.forEach() { game ->
                var possibleGame = true
                for (grab in game.grabs) {
                    if (grab.red > redMax || grab.blue > blueMax || grab.green > greenMax) {
                        //once we see an impossible  game, we can stop going through
                        //the rest of the grabs
                        possibleGame = false
                        continue
                    }
                }
                if (possibleGame) possibleGames.add(game)
            }
            return possibleGames
        }

    }

}