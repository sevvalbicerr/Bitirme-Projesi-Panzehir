package com.example.panzehir.viewModelPatient.gamesViewModel

import android.app.Application
import com.example.panzehir.viewModelPatient.BaseViewModel
import com.example.panzehir.view_Patient.games.sudoku.game.SudokuGame

class SudokuViewModel(application: Application) : BaseViewModel(application) {
    val sudokuGame = SudokuGame()
}