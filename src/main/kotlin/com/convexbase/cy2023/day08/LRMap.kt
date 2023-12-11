package com.convexbase.cy2023.day08

data class LRMap(
    val directions: List<Char>,
    val nodes: List<Triple<String,String,String>>
) {
    val nodeMap = nodes.associateBy{ it.first}

    private data class FollowingState(
        var currentNode:String,
        var count: Int
    )

    fun allStarts():List<String> = nodes.filter{ it.first.endsWith('A')}.map{ it.first }
    private fun allEnds(states: List<FollowingState>): Boolean {
        for (state in states) if (!state.currentNode.endsWith('Z')) return false
        return true
    }

    fun followPath(starts: List<String>, limit: Long = 10_000_000,):List<Pair<String, Long>> {
        var index = 0L
        val followingStates = List<FollowingState>(starts.size) {
            FollowingState(
                currentNode = starts[it],
                count = 0
            )
        }
        while (index < limit) {
            val direction = directions[(index % directions.size).toInt()]
            when (direction) {
                'L' -> followingStates.forEach { followingState ->
                        followingState.currentNode = nodeMap[followingState.currentNode]?.second
                            ?:throw IllegalArgumentException("YIKES Off Track")
                        followingState.count++
                }
                'R' -> followingStates.forEach { followingState ->
                        followingState.currentNode = nodeMap[followingState.currentNode]?.third
                            ?:throw IllegalArgumentException("YIKES Off Track")
                        followingState.count++
                }
            }
            index++
            if (allEnds(followingStates)) return followingStates.map{ Pair(it.currentNode, index) }
        }
        return listOf(Pair("Limit Reached", index))
    }

    fun followSinglePath(start: String, limit: Long = 10_000_000): Pair<String, Long> {
        var index = 0L
        val followingState =
            FollowingState(
                currentNode = start,
                count = 0
            )

        while (index < limit) {
            val direction = directions[(index % directions.size).toInt()]
            when (direction) {
                'L' -> {
                    followingState.currentNode = nodeMap[followingState.currentNode]?.second
                        ?: throw IllegalArgumentException("YIKES Off Track")
                    followingState.count++
                }

                'R' -> {
                    followingState.currentNode = nodeMap[followingState.currentNode]?.third
                        ?: throw IllegalArgumentException("YIKES Off Track")
                    followingState.count++
                }
            }
            index++
            if (followingState.currentNode.endsWith('Z')) return Pair(followingState.currentNode, index)
        }
        throw IllegalStateException("Limit of $limit reached")
    }

    fun followAllPaths(starts: List<String>, limit: Long = 10_000_000):List<Pair<String, Long>> {
        return starts.map{followSinglePath(it)}
    }
    companion object {
        fun lineToTriple(line: String):Triple<String,String,String> {
            val (start, left, right) = line.trim().split(" = (", ", ", ")")
            return Triple(start, left, right)
        }

        fun toLRMap(input: List<String>) = LRMap(
            directions = input.first().trim().toCharArray().toList(),
            nodes = input.drop(1).filter{ it.isNotBlank() }.map { lineToTriple(it) }
        )
    }
}
