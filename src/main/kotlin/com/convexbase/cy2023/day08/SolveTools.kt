package com.convexbase.cy2023.day08

class SolveTools {

    companion object {
        fun lcm(a: Long, b: Long): Long {
            return a * b / gcd(a, b)
        }

        fun gcd(a: Long, b: Long): Long {
            if (b == 0L) return a
            return gcd(b, a % b)
        }

        fun lcm(numbers: List<Long>): Long {
            var result = numbers[0]
            for (i in 1 until numbers.size) {
                result = lcm(result, numbers[i])
            }
            return result
        }
    }
}