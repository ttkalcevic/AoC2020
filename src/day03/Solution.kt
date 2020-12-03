package day03

import common.readFileAsStringList
import java.math.BigInteger

const val PUZZLE_INPUT_PATH = "./src/day03/PuzzleInput.txt"

const val TREE_SQUARE: Char = '#'

fun main(args: Array<String>) {
    val puzzleInput = readFileAsStringList(PUZZLE_INPUT_PATH)
    val grid = getInputGrid(puzzleInput)
    solutionA(grid)
    println()
    solutionB(grid)
}

private fun solutionA(grid: ArrayList<CharArray>) {
    val treesCount = checkSlope(grid, 3, 1)

    println("Solution A: $treesCount trees encountered.")
}

private fun solutionB(grid: ArrayList<CharArray>) {
    val treesCount = arrayListOf<Int>()

    val slopes = arrayListOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))

    slopes.forEach { slope ->
        val treesEncountered = checkSlope(grid, slope.first, slope.second)
        println("Slope ${slope.first}-${slope.second}: $treesEncountered trees encountered.")
        treesCount.add(treesEncountered)
    }

    var solution: BigInteger = BigInteger.ONE
    treesCount.forEach { solution *= it.toBigInteger() }
    println("Solution B: $solution")
}

private fun checkSlope(grid: ArrayList<CharArray>, right: Int = 1, down: Int = 1): Int {
    var xCoordinate = 0
    var treesCount = 0

    for (y in down until grid.size step down) {
        xCoordinate = (xCoordinate + right) % grid[y].size
        if (grid[y][xCoordinate] == TREE_SQUARE) treesCount++
    }

    return treesCount
}


private fun getInputGrid(input: List<String>): ArrayList<CharArray> =
        arrayListOf<CharArray>().also { grid -> input.forEach { grid.add(it.toCharArray()) } }
