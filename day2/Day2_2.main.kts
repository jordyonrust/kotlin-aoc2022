#!/usr/bin/env kotlin

import java.io.File

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun parseInput(input: String): List<String> = input.lines().map(String::trim)

fun calcOutcome(round: String): Int {
    return when (round[2]) {
        'X' -> 0 // Lost
        'Y' -> 3 // Draw
        'Z' -> 6 // Win
        else -> throw IllegalArgumentException("Unrecognized input: $round")
    }
}

fun calcShape(round: String): Int {
    return when (round) {
        "B X", "A Y", "C Z" -> 1 // Rock
        "C X", "B Y", "A Z" -> 2 // Paper
        "A X", "C Y", "B Z" -> 3 // Scissors
        else -> throw IllegalArgumentException("Unrecognized input: $round")
    }
}

fun playRound(round: String): Int {
   return calcOutcome(round) + calcShape(round)
}

fun List<String>.playGame(): Int {
    return sumOf { playRound(it) }
}

val day2TestInput = File("Day2TestInput.txt").readText()
val day2Input = File("Day2Input.txt").readText()

parseInput(day2TestInput).playGame().println()
parseInput(day2Input).playGame().println()
