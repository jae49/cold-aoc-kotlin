package com.convexbase.cy2019

class ElfComputer(val program: ArrayList<Int>) {

    val programCounter = 0

    fun run() {

    }

    fun step() {

    }

    companion object {
        fun getProgram(programStr: String): ArrayList<Int> {
            return programStr.trim().split(',').map { it.toInt() }.toCollection(ArrayList())
        }

        fun getProgramResource(resourceFilePath: String): ArrayList<Int> {
            return getProgram(ElfComputer::class.java.getResource(resourceFilePath).readText())
        }
    }
}