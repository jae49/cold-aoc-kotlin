package com.convexbase.cy2019.day02

class SimpleComputer {

    enum class OpCode(val code:Int, val iLength: Int) {
        Add(1, 4),
        Multiply(2, 4),
        Halt(99, 1),
        Error(-1, 0);


        companion object {
            fun getByCode(c: Int):OpCode {
                entries.forEach { if (it.code == c) return it}
                return Error
            }
        }
    }

    sealed class OpResult {
        data object Halt: OpResult()
        class Error(val pc: Int): OpResult()
        class Success(val pc: Int): OpResult()
    }


    companion object {
        fun run(program: ArrayList<Int>): OpResult {
            var pc = 0
            var stopped = false
            var result: OpResult = OpResult.Error(0)
            while (!stopped) {
                result = step(pc, program)
                when (result) {
                    is OpResult.Halt -> stopped = true
                    is OpResult.Error -> stopped = true
                    is OpResult.Success -> pc = result.pc
                }
                if (pc >= program.size) return OpResult.Error(pc)
            }
            return result
        }

        fun step(pc: Int, program: ArrayList<Int>): OpResult {
            val opcode = OpCode.getByCode(program[pc])
            when (opcode) {
                OpCode.Add -> {
                    val firstPos = program[pc+1]
                    val secondPos = program[pc+2]
                    val firstVal = program[firstPos]
                    val secondVal = program[secondPos]
                    val destination = program[pc+3]
                    program[destination] = firstVal + secondVal
                    return OpResult.Success(pc + opcode.iLength)
                }
                OpCode.Multiply -> {
                    val firstPos = program[pc+1]
                    val secondPos = program[pc+2]
                    val firstVal = program[firstPos]
                    val secondVal = program[secondPos]
                    val destination = program[pc+3]
                    program[destination] = firstVal * secondVal
                    return OpResult.Success(pc + opcode.iLength)
                }
                OpCode.Halt -> {
                    return OpResult.Halt
                }
                OpCode.Error -> {
                    return OpResult.Error(pc)
                }
            }
        }
    }
}