#!/usr/bin/env kotlin

import java.io.File

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

val day1Input = File("Day1Input.txt").readText()

fun parseInput(input: String) = input.split("\n\n").map { elf ->
    elf.lines().map { it.toInt() }
}

fun List<List<Int>>.topNElves(n: Int): Int {
    return map { it.sum() }
        .sortedDescending()
        .take(n)
        .sum()
}

parseInput(day1Input).topNElves(1).println()
parseInput(day1Input).topNElves(3).println()