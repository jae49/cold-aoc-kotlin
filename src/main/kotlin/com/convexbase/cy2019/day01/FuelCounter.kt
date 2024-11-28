package com.convexbase.cy2019.day01

class FuelCounter {

    companion object {
        fun getFuel(mass: Long): Long {
            return mass / 3 - 2
        }

        fun getIterativeFuel(mass: Long): Long {
            var total = 0L
            var currentMass = mass
            while (currentMass > 0) {
                currentMass = getFuel(currentMass)
                if (currentMass > 0) {
                    total += currentMass
                }
            }
            return total
        }
    }
}