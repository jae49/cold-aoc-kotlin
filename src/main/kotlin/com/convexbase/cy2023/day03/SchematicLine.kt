package com.convexbase.cy2023.day03

data class SchematicLine(
    val lineNo: Int,
    val parts: List<PartSymbol>,
    val partNumbers: List<PartNo>,
    val rawLine: String
) {
    companion object {
        fun toSchematicLine(lineNo: Int, line: String): SchematicLine {
            val trimmedLine = line.trim()
            return SchematicLine(
                lineNo = lineNo,
                parts = getParts(lineNo, trimmedLine),
                partNumbers = getPartNumbers(lineNo, trimmedLine),
                rawLine = line
            )
        }

        fun getPartNumbers(lineNo: Int, line:String): List<PartNo> {
            val chars = "${line}.".toCharArray() // done to address trailing number case
            val partNoList = mutableListOf<PartNo>()
            var startIndex = -1
            val partNoChars = mutableListOf<Char>()
            for (i in 0.rangeUntil(chars.size)) {
                val c = chars[i]
                if (c.isDigit()) {
                    partNoChars.add(c)
                    if (startIndex == -1)  startIndex = i
                } else {
                    if (startIndex != -1) {
                        // digits have ended, create the part number
                        partNoList.add(PartNo(
                            lineNo = lineNo,
                            startIndex = startIndex,
                            endIndex = i - 1,
                            partStr = partNoChars.joinToString("")
                        ))
                        //end of part No, reset startIndex and chars
                        startIndex = -1
                        partNoChars.clear()
                    }
                }
            }
            return partNoList
        }

        fun getParts(lineNo: Int, line: String): List<PartSymbol> {
            val chars = line.toCharArray()
            val partsList = mutableListOf<PartSymbol>()
            for (i in 0.rangeUntil(chars.size)) {
                val c = chars[i]
                if (c.isDigit() ) continue
                if (c.isWhitespace()) continue
                if (c == '.') continue
                partsList.add(PartSymbol(
                    lineNo = lineNo,
                    symbol = c,
                    index = i
                ))
            }
            return partsList
        }
    }
}
