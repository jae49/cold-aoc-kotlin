package com.convexbase.cy2019.day01

import kotlin.test.Test
import kotlin.test.assertEquals

class FuelCounterTests {

    @Test
    fun calculatePart1TestInputs() {
        assertEquals(2, FuelCounter.getFuel(12))
        assertEquals(2, FuelCounter.getFuel(14))
        assertEquals(654, FuelCounter.getFuel(1969))
        assertEquals(33583, FuelCounter.getFuel(100756))
    }

    @Test
    fun calculatPart2TestInputs() {
        assertEquals(2, FuelCounter.getIterativeFuel(14))
        assertEquals(966, FuelCounter.getIterativeFuel(1969))
        assertEquals(50346, FuelCounter.getIterativeFuel(100756))
    }
}