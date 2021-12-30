package com.example.panzehir.view_Patient.games.sudoku.game

class Board(val size: Int, val cells: List<Cell>) {

    fun getCell(row: Int, col: Int) = cells[row * size + col]
    //buraaya çözümü görmek için tıklanılacak
// fonk'da kullanılması için tüm kullnıcı girişlerini getiren fonk
    // yazılması gerekiyor.
    fun getAllCell() {
        for (i in 0 until size) {
            //satırları basıyor(0. satırı)
               // println(cells[i].value)
                }
        }
    }
