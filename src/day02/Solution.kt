package day02

import common.readFileAsStringList

const val PUZZLE_INPUT_PATH = "./src/day02/PuzzleInput.txt"

val INPUT_REGEX = """([0-9]+)-([0-9]+)\s([a-z]):\s([a-z]*)""".toRegex()

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val inputList = readFileAsStringList(PUZZLE_INPUT_PATH)
    val passwordPolicyRules: ArrayList<PasswordPolicy> = arrayListOf()
    inputList.forEach { policy -> passwordPolicyRules.add(parsePasswordPolicy(policy)) }

    solutionA(passwordPolicyRules)
    solutionB(passwordPolicyRules)
}

fun solutionA(passwordPolicyRules: ArrayList<PasswordPolicy>) {
    val validPasswords = passwordPolicyRules.count { it.isCharCountValid() }
    println("Solution A: $validPasswords valid passwords")
}

fun solutionB(passwordPolicyRules: ArrayList<PasswordPolicy>) {
    val validPasswords = passwordPolicyRules.count { it!!.isCharPositionValid() }
    println("Solution B: $validPasswords valid passwords")
}

private fun parsePasswordPolicy(input: String): PasswordPolicy {
    val parsedInput = INPUT_REGEX.matchEntire(input)
    return PasswordPolicy(parsedInput!!.groupValues[1].toInt(), parsedInput.groupValues[2].toInt(), parsedInput.groupValues[3].toCharArray().first(), parsedInput.groupValues[4])
}

data class PasswordPolicy(val firstParameter: Int, val secondParameter: Int, val char: Char, val password: String) {
    fun isCharCountValid(): Boolean = password.count { it == char } in firstParameter..secondParameter

    fun isCharPositionValid(): Boolean {
        val firstChar = password[firstParameter - 1]
        val secondChar = password[secondParameter - 1]

        return if (firstChar == char && secondChar != char) {
            true
        } else firstChar != char && secondChar == char
    }
}
