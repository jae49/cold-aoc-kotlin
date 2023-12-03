package com.convexbase.cy2023.day01

class SolveTools {

    companion object {
        @JvmStatic
        fun totalFLNumberStringsInFile(rsrcName: String):Int {
            var total = 0
            SolveTools::class.java.getResource(rsrcName).readText().lines().forEach { line ->
                total += getFirstLastNumber(line)
            }
            return total
        }

        @JvmStatic
        fun totalUglyFLNumberStringsInFile(rsrcName: String):Int {
            var total = 0
            SolveTools::class.java.getResource(rsrcName).readText().lines().forEach { line ->
                total += getUglyFirstLastNumber(line)
            }
            return total
        }

        /**
         * Gets the first and last number from a string and forms a two digit number
         * In the event that there is only one number, that number is the first and last digit in
         * in the two digit number.
         *
         * Warning: In the event that there are no numbers in the string, 0 is
         * returned instead of an error.  This should be fine for the intended use of adding
         * all the numbers together later.
         */
        @JvmStatic
        fun getFirstLastNumber(s: String):Int {
            var firstNumChar = '0'
            var lastNumChar = '0'
            var firstFound = false
            s.forEach{ c ->
                if (c.isDigit()) {
                    if (!firstFound) {
                        firstNumChar = c
                        firstFound = true
                    }
                    lastNumChar = c
                }
            }
            return "$firstNumChar$lastNumChar".toInt()
        }

        fun getUglyNumber(index: Int, chars: CharArray):Char? {
            val c = chars[index]
            if (c.isDigit()) return c
            else when (c) {
                'o' -> {
                    // check for one
                    if (chars.size - index >= 3) {
                        if (chars[index+1] == 'n' && chars[index+2] == 'e') return '1'
                    }
                }
                't' -> {
                    // check for two, three
                    if (chars.size - index >= 3) {
                        if (chars[index+1] == 'w' && chars[index+2] == 'o') return '2'
                    }
                    if (chars.size - index >= 5) {
                        if (chars[index+1] == 'h' && chars[index+2] == 'r'
                            && chars[index+3] == 'e' && chars[index+4] == 'e') return '3'
                    }
                }
                'f' -> {
                    // check for four, five
                    if (chars.size - index >= 4) {
                        if (chars[index+1] == 'o' && chars[index+2] == 'u'
                            && chars[index+3] == 'r') return '4'
                        if (chars[index+1] == 'i' && chars[index+2] == 'v'
                            && chars[index+3] == 'e') return '5'
                    }
                }
                's' -> {
                    // check for six, seven
                    if (chars.size - index >= 3) {
                        if (chars[index+1] == 'i' && chars[index+2] == 'x') return '6'
                    }
                    if (chars.size - index >= 5) {
                        if (chars[index+1] == 'e' && chars[index+2] == 'v'
                            && chars[index+3] == 'e' && chars[index+4] == 'n') return '7'
                    }
                }
                'e' -> {
                    // check for eight
                    if (chars.size - index >= 5) {
                        if (chars[index+1] == 'i' && chars[index+2] == 'g'
                            && chars[index+3] == 'h' && chars[index+4] == 't') return '8'
                    }
                }
                'n' -> {
                    // check for nine
                    if (chars.size - index >= 4) {
                        if (chars[index+1] == 'i' && chars[index+2] == 'n'
                            && chars[index+3] == 'e') return '9'
                    }
                }
                'z' -> {
                    // check for zero
                    if (chars.size - index >= 4) {
                        if (chars[index+1] == 'e' && chars[index+2] == 'r'
                            && chars[index+3] == 'o') return '0'
                    }
                }
            }
            return null
        }

        @JvmStatic
        fun getUglyFirstLastNumber(s: String):Int {
            val chars = s.lowercase().toCharArray()
            var firstFound = false
            var firstNumChar: Char = '0'
            var lastNumChar: Char = '0'
            for (i in 0..(chars.size-1)) {
                when (val c = getUglyNumber(i, chars)) {
                    null -> continue
                    else -> {
                        if (!firstFound) {
                            firstNumChar = c
                            firstFound = true
                        }
                        lastNumChar = c
                    }
                }
            }
            return "$firstNumChar$lastNumChar".toInt()
        }
    }
}