package com.example.panzehir.view_Patient.games.sudoku.game

class Board(val size: Int, val cells: List<Cell>) {

    fun getCell(row: Int, col: Int) = cells[row * size + col]

    }
