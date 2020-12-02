package day01

import common.CombinationGenerator
import extensions.multiply
import common.readFileAsIntList

const val PUZZLE_INPUT_PATH = "./src/day01/PuzzleInput.txt"
const val DESIRED_RESULT = 2020

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val puzzleInput = readFileAsIntList(PUZZLE_INPUT_PATH)
    solutionA(puzzleInput)
    solutionB(puzzleInput)
}

private fun solutionA(list: List<Int>) {
    val combinations = CombinationGenerator(list, 2)
    val result = combinations.filter { combination -> combination.sum() == DESIRED_RESULT }
    println("Solution A: ${result.first()} -> ${result.first().multiply()}")
}

private fun solutionB(list: List<Int>) {
    val combinations = CombinationGenerator(list, 3)
    val result = combinations.filter { combination -> combination.sum() == DESIRED_RESULT }
    println("Solution B: ${result.first()} -> ${result.first().multiply()}")
}
