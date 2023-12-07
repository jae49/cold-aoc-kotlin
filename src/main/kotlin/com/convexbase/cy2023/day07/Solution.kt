package com.convexbase.cy2023.day07

class Solution {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val input = Solution::class.java.getResource("/data/2023/day07/input.txt").readText().lines()
                .filter{it.isNotBlank()}
            println("Input line count: ${input.size}")

            //part 1
            val p1SortedBids = CamelCardBid.getCamelCardBidList(input).sorted()
            val p1answer = CamelCardBid.getTotalBid(p1SortedBids)
            println("Part 1: $p1answer")

            // part 2
            val p2SortedBids =  CamelCardBid.getCamelCardBidList(input, jokersWild = true).sorted()
            val p2answer = CamelCardBid.getTotalBid(p2SortedBids)
            println("Part 2: $p2answer")
        }

    }
}