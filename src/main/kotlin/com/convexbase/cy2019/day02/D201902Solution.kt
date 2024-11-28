package com.convexbase.cy2019.day02

import com.convexbase.cy2019.ElfComputer

class D201902Solution {

    companion object {
        fun getPart1Answer(program: ArrayList<Int>):Int {
            program[1] = 12
            program[2] = 2
            val result = SimpleComputer.run(program)
            when (result) {
                is SimpleComputer.OpResult.Halt -> return program[0]
                is SimpleComputer.OpResult.Error ->
                    throw IllegalStateException("Error at program counter ${result.pc}")
                is SimpleComputer.OpResult.Success ->
                    throw IllegalStateException("Unexpected end at program counter ${result.pc}")
            }
        }

        fun deepCopy(program:ArrayList<Int>):ArrayList<Int> {
            return ArrayList(program.map {it}.toList())
        }

        fun getPart2Answer(program: ArrayList<Int>):Pair<Int,Int> {
            for (noun in 0..99) {
                for (verb in 0 .. 99) {
                    val temp = deepCopy(program)
                    print("noun: $noun, verb: $verb")
                    temp[1] = noun
                    temp[2] = verb
                    val result = SimpleComputer.run(temp)
                    when (result) {
                        is SimpleComputer.OpResult.Halt -> {
                            println(" -> ${temp[0]}")
                            if (temp[0] == 19690720) return Pair(noun, verb)
                        }
                        is SimpleComputer.OpResult.Error -> {
                            println(" -> error ${result.pc}")
                        }
                        is SimpleComputer.OpResult.Success -> {
                            println(" -> unexpected end ${result.pc}")
                        }
                    }
                }
            }
            throw IllegalStateException("No noun/verb combination found")
        }


        @JvmStatic
        fun main(args: Array<String>) {
            val program = ElfComputer.getProgramResource("/data/2019/day02/input.txt")
            val part1Answer = getPart1Answer(program)
            val program2 = ElfComputer.getProgramResource("/data/2019/day02/input.txt")
            val part2Answer = getPart2Answer(program2)
            println("Part 1: $part1Answer")
            println("Part 2: $part2Answer")
            println("Part 2: ${part2Answer.first * 100 + part2Answer.second}")
        }
    }
}