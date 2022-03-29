package tictactoe

import kotlin.math.abs

fun drawBoard(board: Array<CharArray>) {
    val size = board.size * board.size
    println("-".repeat(size))
    for (row in board) {
        print("| ")
        for (cell in row) {
            print("$cell ")
        }
        println("|")
    }
    println("-".repeat(size))
}

fun calculateWinner(board: Array<CharArray>): String {
    // Check impossible cases
    var fullSetX = 0
    var fullSetO = 0
    for (row in board) {
        if (row.all { it == 'X' }) {
            fullSetX = 1
        }
        if (row.all { it == 'O' }) {
            fullSetO = 1
        }
    }

    if (fullSetX == 1 && fullSetO == 1) {
        return "Impossible"
    }

    fullSetX = 0
    fullSetO = 0

    for (i in board.indices) {
        if (board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X') {
            fullSetX = 1
        }
        if (board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O') {
            fullSetO = 1
        }
    }

    if (fullSetX == 1 && fullSetO == 1) {
        return "Impossible"
    }

    var countX = 0
    var countY = 0
    for (row in board) {
        for (cell in row) {
            if (cell == 'X') {
                countX++
            }
            if (cell == 'O') {
                countY++
            }
        }
    }

    if (abs(countX - countY) >= 2) {
        return "Impossible"
    }


    // Look X or O wins in rows
    for (row in board) {
        if (row[0] == 'X' && row[1] == 'X' && row[2] == 'X') {
            return "X wins"
        }
        if (row[0] == 'O' && row[1] == 'O' && row[2] == 'O') {
            return "O wins"
        }
    }
    // Look X or O wins in columns
    for (i in board.indices) {
        if (board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X') {
            return "X wins"
        }
        if (board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O') {
            return "O wins"
        }
    }

    // Look X or O wins in diagonals
    if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
        return "X wins"
    }
    if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') {
        return "O wins"
    }

    // Look X or O wins in reverse diagonals
    if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {
        return "X wins"
    }
    if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
        return "O wins"
    }

    // Check if the board has at least three empty spaces
    var emptySpaces = 0
    for (row in board) {
        for (cell in row) {
            if (cell == ' ') {
                emptySpaces++
            }
        }
    }
    if (emptySpaces >= 3) {
        return "Game not finished"
    }
    if (emptySpaces == 0) {
        return "Draw"
    }
    return " "
}

fun main() {
    val board = Array(3) { CharArray(3) { ' ' } }
    var startSign = 'X'
    drawBoard(board)

    while (true) {
        print("Enter the coordinates: ")
        val coordinates = readln().split(" ")
        val x: Int
        val y: Int

        try {
            x = coordinates[0].toInt() - 1
            y = coordinates[1].toInt() - 1
        } catch (e: Exception) {
            println("You should enter numbers!")
            continue
        }

        if (x !in 0..2 || y !in 0..2) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        if (board[x][y] != ' ') {
            println("This cell is occupied! Choose another one!")
            continue
        }
        board[x][y] = startSign
        startSign = if (startSign == 'X') {
            'O'
        } else {
            'X'
        }
        drawBoard(board)
        val winner = calculateWinner(board)
        if (winner == "X wins" || winner == "O wins" || winner == "Draw") {
            println(winner)
            break
        }
    }


}
