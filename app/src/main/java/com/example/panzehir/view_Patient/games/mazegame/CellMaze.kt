package com.example.panzehir.view_Patient.games.mazegame

class CellMaze(var col:Int, var row: Int) {

    var topWall: Boolean = true
    var leftWall: Boolean = true
    var bottomWall: Boolean = true
    var rightWall: Boolean = true
    var visited: Boolean = false
}