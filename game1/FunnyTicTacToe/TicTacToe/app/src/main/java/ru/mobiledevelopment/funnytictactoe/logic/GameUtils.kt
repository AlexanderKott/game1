package ru.mobiledevelopment.funnytictactoe.logic


class GameState(
    var xScores: Int,
    var oScores: Int,
    val fieldVariants: Array<Array<String>>,
    var gameField: Array<Array<String>>,
    var isCheating: Boolean
)


fun initGameField(): GameState {
    val field = Array(3) { arrayOf("_", "_", "_") }
    return GameState(  0, 0, initVariants(), field, false)
}

class GameResult(var weHaveWinner: Boolean, var winner: Int, var WinnersRow: Array<Int>)

