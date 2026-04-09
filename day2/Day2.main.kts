#!/usr/bin/env kotlin

import java.io.File

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

enum class Outcome(val points: Int) {
    LOST(0),
    DRAW(3),
    WIN(6)
}

enum class RockPaperScissorsShape(val points: Int) {
    ROCK(1) {
        override fun play(opponentShape: RockPaperScissorsShape): Outcome {
            return when (opponentShape) {
                ROCK -> Outcome.DRAW
                PAPER -> Outcome.WIN
                SCISSORS -> Outcome.LOST
            }
        }
    },
    PAPER(2) {
        override fun play(opponentShape: RockPaperScissorsShape): Outcome {
            return when (opponentShape) {
                ROCK -> Outcome.LOST
                PAPER -> Outcome.DRAW
                SCISSORS -> Outcome.WIN
            }
        }
    },
    SCISSORS(3) {
        override fun play(opponentShape: RockPaperScissorsShape): Outcome {
            return when (opponentShape) {
                ROCK -> Outcome.WIN
                PAPER -> Outcome.LOST
                SCISSORS -> Outcome.DRAW
            }
        }
    };

    abstract fun play(opponentShape: RockPaperScissorsShape): Outcome

    companion object {
        fun fromChar(char: Char): RockPaperScissorsShape {
            when (char) {
                'A' -> return ROCK
                'B' -> return PAPER
                'C' -> return SCISSORS
            }
            throw IllegalArgumentException("Unrecognized input: $char")
        }

        fun parseStrategy(input: Char, shapeOpponent: RockPaperScissorsShape): RockPaperScissorsShape {
            when (input) {
                // Lose
                'X' -> return when (shapeOpponent) {
                    ROCK -> SCISSORS
                    PAPER -> ROCK
                    SCISSORS -> PAPER
                }
                // Draw
                'Y' -> return shapeOpponent
                // WIN
                'Z' -> return when (shapeOpponent) {
                    ROCK -> PAPER
                    PAPER -> SCISSORS
                    SCISSORS -> ROCK
                }
            }
            throw IllegalArgumentException("Unrecognized input: $input")
        }
    }
}

fun parseShapes(round: String): Pair<RockPaperScissorsShape, RockPaperScissorsShape> {
    val opponent = RockPaperScissorsShape.fromChar(round[0])
    val shapeToPlay = RockPaperScissorsShape.parseStrategy(round[2], opponent)
    return Pair(opponent, shapeToPlay)
}

fun playRound(round: String): Int {
    val playedShapes = parseShapes(round)
    val outcome = playedShapes.first.play(playedShapes.second)
    return calcPoints(outcome, playedShapes.second)
}

fun calcPoints(outcome: Outcome, playedShape: RockPaperScissorsShape): Int {
    return outcome.points + playedShape.points
}

fun parseInput(input: String): List<String> = input.lines().map(String::trim)

fun List<String>.playGame(): Int {
    return sumOf { playRound(it) }
}

val day2TestInput = File("Day2TestInput.txt").readText()
val day2Input = File("Day2Input.txt").readText()

parseInput(day2TestInput).playGame().println()
parseInput(day2Input).playGame().println()
