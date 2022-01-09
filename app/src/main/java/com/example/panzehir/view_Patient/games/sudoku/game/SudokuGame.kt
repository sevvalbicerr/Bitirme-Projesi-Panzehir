package com.example.panzehir.view_Patient.games.sudoku.game

import androidx.compose.ui.input.key.Key.Companion.K
import androidx.lifecycle.MutableLiveData
import com.google.android.play.core.splitinstall.d

class SudokuGame {

    var selectedCellLiveData = MutableLiveData<Pair<Int,Int>>()
    var cellsLiveData = MutableLiveData<List<Cell>>()
    val isTakingNotesLiveData = MutableLiveData<Boolean>()
    val highlightedKeysLiveData = MutableLiveData<Set<Int>>()
    var boards= Array(9) { IntArray(9) }
    var cells : List<Cell>
    private var selectedRow = -1
    private var selectedCol = -1
    private var isTakingNotes = false
    private var board: Board

    /* Uygulamadaki note ve silme butonunu , sayılar butonunu uygulama tlamadan kullanabilmek için önce boardTan bir yere tıklamak gerekiyor.
*/
    init {
        //uygulama ilk çalıştığında dolu gelmesini sağlayan kod bu satır. value değişkenine ne verirsen
        //tablo onunla dolu olarak geliyor.
        boards=generateSudokuPuzzle()
        cells = List(9 * 9){i -> Cell( i / 9, i % 9 , boards[i / 9][i % 9], wrong = true)}
        board = Board(9, cells)
        selectedCellLiveData.postValue(Pair(selectedRow,selectedCol))
        cellsLiveData.postValue(board.cells)
    }
    fun handleInput(number: Int){
        val cell = board.getCell(selectedRow,selectedCol)
                // Check control
        if (selectedRow == -1 || selectedCol == -1) return
        if (cell.isStartingCell) return
        if (isTakingNotes){
            if (cell.notes.contains(number)){
                cell.notes.remove(number)
            } else{
                cell.notes.add(number)
            }
            highlightedKeysLiveData.postValue(cell.notes)
        }
        board.getCell(selectedRow, selectedCol).value = number
        solveSudoku(boards,boards.size)
        cell.wrong = !(cell.value!=boards[selectedRow][selectedCol] && cell.value!=0)
        cellsLiveData.postValue(board.cells)

    }
    fun updateSelectedCell(row: Int, col: Int){
        val cell = board.getCell(row, col)
        if (!cell.isStartingCell) {
            selectedRow = row
            selectedCol = col
            selectedCellLiveData.postValue(Pair(row, col))
        }
        if (isTakingNotes){
            highlightedKeysLiveData.postValue(cell.notes)
        }

    }
    // Edit the pen
    fun changeNoteTakingState(){
        isTakingNotes = !isTakingNotes
        isTakingNotesLiveData.postValue(isTakingNotes)

        val curNotes = if (isTakingNotes){
            board.getCell(selectedRow,selectedCol).notes
        }else{ setOf<Int>()}
        highlightedKeysLiveData.postValue(curNotes)

    }
    fun delete(){
        val cell = board.getCell(selectedRow,selectedCol)
        if (isTakingNotes){
            cell.notes.clear()
            highlightedKeysLiveData.postValue(setOf())
        }else{cell.value = 0}

        cellsLiveData.postValue(board.cells)

    }
    private fun generateSudokuPuzzle(): Array<IntArray> {
        //Generate sudoku to ready to solve
        val N = 9
        val level = 30
        val sudoku = Generator(N, level)
        return sudoku.fillValues()
    }

    fun solve(){


        solveSudoku(boards,boards.size)
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                board.getCell(i, j).value = boards[i][j]

            }
        }
        cellsLiveData.postValue(board.cells)
    }
    fun newGame(){
        boards=generateSudokuPuzzle()
        cells = List(9 * 9){i -> Cell( i / 9, i % 9 , boards[i / 9][i % 9], wrong = true)}
        board = Board(9, cells)
        cellsLiveData.postValue(board.cells)

    }
    private fun getValueCell(){
        //User enter solutions
        for (r in 0 until 9) {
            for (d in 0 until 9) {
                print(board.getCell(r, d).value)
                print(" ")
            }
            print("\n")
            if ((r + 1) % Math.sqrt(9.toDouble()).toInt() == 0) {
                print("")
            }
        }
    }
    //Solver
    fun isSafe(
        board: Array<IntArray>,
        row: Int, col: Int,
        num: Int
    ): Boolean {
        // Row has the unique (row-clash)
        for (d in board.indices) {

            // Check if the number we are trying to
            // place is already present in
            // that row, return false;
            if (board[row][d] == num) {
                return false
            }
        }
        // Column has the unique numbers (column-clash)
        for (r in board.indices) {

            // Check if the number
            // we are trying to
            // place is already present in
            // that column, return false;
            if (board[r][col] == num) {
                return false
            }
        }

        // Corresponding square has
        // unique number (box-clash)
        val sqrt = Math.sqrt(board.size.toDouble()).toInt()
        val boxRowStart = row - row % sqrt
        val boxColStart = col - col % sqrt
        for (r in boxRowStart until boxRowStart + sqrt) {
            for (d in boxColStart until boxColStart + sqrt) {
                if (board[r][d] == num) {
                    return false
                }
            }
        }

        // if there is no clash, it's safe
        return true
    }

    fun solveSudoku(
        board: Array<IntArray>, n: Int
    ): Boolean {
        var row = -1
        var col = -1
        var isEmpty = true
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (board[i][j] == 0) {
                    row = i
                    col = j

                    // We still have some remaining
                    // missing values in Sudoku
                    isEmpty = false
                    break
                }
            }
            if (!isEmpty) {
                break
            }
        }

        // No empty space left
        if (isEmpty) {
            return true
        }

        // Else for each-row backtrack
        for (num in 1..n) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num
                if (solveSudoku(board, n)) {
                    // print(board, n);
                    return true
                } else {
                    // replace it
                    board[row][col] = 0
                }
            }
        }
        return false
    }

    fun print_(
        board: Array<IntArray>, N: Int
    ) {

        // We got the answer, just print it
        for (r in 0 until N) {
            for (d in 0 until N) {
                print(board[r][d])
                print(" ")
            }
            print("\n")
            if ((r + 1) % Math.sqrt(N.toDouble()).toInt() == 0) {
                print("")
            }
        }
    }



}