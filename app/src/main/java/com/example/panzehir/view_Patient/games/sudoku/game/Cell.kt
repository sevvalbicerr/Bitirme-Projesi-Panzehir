package com.example.panzehir.view_Patient.games.sudoku.game

import android.graphics.Color

class Cell(
    val row: Int,
    val col: Int,
    var value: Int,
    var isStartingCell: Boolean = false,
    var notes: MutableSet<Int> = mutableSetOf(),
    var wrong:Boolean

)