package com.convexbase

fun String.splitByWhitespace():List<String> {
    return this.split("""\s+""".toRegex())
}

object CoreUtils {
    @JvmStatic
    fun getLineLongs(line: String, expectedLength: Int = -1, silentlyDropBadNums: Boolean = false): List<Long> {
        val parts = line.splitByWhitespace()
        if (expectedLength >= 0) {
            check(parts.size == expectedLength) { "Expected $expectedLength elements.  Found ${parts.size}" }
        }
        val longs = mutableListOf<Long>()
        for (part in parts) {
            runCatching {
                part.toLong()
            }.fold(
                onSuccess = { longs.add(it) },
                onFailure = {
                    if (!silentlyDropBadNums) {
                        throw it
                    }
                }
            )

        }
        return longs
    }


    fun readLineFromResource(resource: String): String =
        CoreUtils::class.java.getResourceAsStream(resource).bufferedReader().readText()


    fun readLinesFromResource(resource:String): List<String> =
        CoreUtils::class.java.getResourceAsStream(resource).bufferedReader().readLines().filter(String::isNotBlank)

}