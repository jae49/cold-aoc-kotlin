package com.convexbase.cy2023.day05

data class DEntry(
    val dest: Long,
    val src: Long,
    val range: Long
) {
    val srcRange = src.rangeUntil(src + range)
    operator fun get(index: Long):Long {
        if (index in srcRange) return dest + index - src
        return index
    }

}
class IDEMap(
    val rangeList: List<DEntry>
) {
    operator fun get(index: Long):Long {
        for (range in rangeList) if (index in range.srcRange) return range[index]
        return index
    }

}

data class PuzzleData(
    val seeds: List<Long>,
    val seedToSoil: IDEMap,
    val soilToFertilizer: IDEMap,
    val fertilizerToWater: IDEMap,
    val waterToLight: IDEMap,
    val lightToTemperature: IDEMap,
    val temperatureToHumidity: IDEMap,
    val humidityToLocation: IDEMap
) {
    val seedRanges: List<LongRange> = seeds
        .chunked(2)
        .map{ it[0].rangeUntil(it[0] + it[1])}
    val seedRangesTotalCount = seedRanges.sumOf { it.last - it.first + 1 }
    val seedLocations:List<Long> = seeds
        .map { seedToSoil[it] }
        .map { soilToFertilizer[it] }
        .map { fertilizerToWater[it] }
        .map { waterToLight[it] }
        .map { lightToTemperature[it] }
        .map { temperatureToHumidity[it] }
        .map { humidityToLocation[it] }

    inline fun seedLocation(seed: Long) = humidityToLocation[temperatureToHumidity[lightToTemperature[
        waterToLight[fertilizerToWater[soilToFertilizer[seedToSoil[seed]]]]]]]
    fun lowestRangeSeedLocation():Long {
        val minSeed = seedRanges
            .asSequence()
            .flatten()
            .map { seed -> seedLocation(seed) }
            .minOrNull()

        return minSeed ?: Long.MAX_VALUE
    }


    companion object {

        fun parsePuzzleData(lines: List<String>): PuzzleData {
            var seeds: List<Long>? = null
            val seedToSoil = mutableListOf<DEntry>()
            val soilToFertilizer = mutableListOf<DEntry>()
            val fertilizerToWater = mutableListOf<DEntry>()
            val waterToLight = mutableListOf<DEntry>()
            val lightToTemperature = mutableListOf<DEntry>()
            val temperatureToHumidity = mutableListOf<DEntry>()
            val humidityToLocation = mutableListOf<DEntry>()
            var currentMap: MutableList<DEntry>? = null
            for (line in lines) {
                if (line.isBlank()) {
                    currentMap = null
                    continue
                }
                if (line.contains(":")) {
                    val lineParts = line.split(":")
                    val element = lineParts[0].trim()
                    when (element) {
                        "seeds" -> seeds = line.split(":")[1].trim().split(" ").map { it.toLong() }
                        "seed-to-soil map" -> currentMap = seedToSoil
                        "soil-to-fertilizer map" -> currentMap = soilToFertilizer
                        "fertilizer-to-water map" -> currentMap = fertilizerToWater
                        "water-to-light map" -> currentMap = waterToLight
                        "light-to-temperature map" -> currentMap = lightToTemperature
                        "temperature-to-humidity map" -> currentMap = temperatureToHumidity
                        "humidity-to-location map" -> currentMap = humidityToLocation
                    }
                    continue
                }
                //line is not blank and doesnt contain a colon, so it must be data lines for a map
                val (dest, src, range) = line.trim().split(" ").map{ it.toLong()}
                currentMap?.add(DEntry(
                    dest = dest,
                    src = src,
                    range = range
                ))
            }
            return PuzzleData(
                seeds = seeds?:emptyList<Long>(),
                seedToSoil = IDEMap(seedToSoil),
                soilToFertilizer = IDEMap(soilToFertilizer),
                fertilizerToWater = IDEMap(fertilizerToWater),
                waterToLight = IDEMap(waterToLight),
                lightToTemperature = IDEMap(lightToTemperature),
                temperatureToHumidity = IDEMap(temperatureToHumidity),
                humidityToLocation = IDEMap(humidityToLocation)
            )
        }
    }
}