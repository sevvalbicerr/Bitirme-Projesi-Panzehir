package com.example.panzehir.view_Patient.games.mazegame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates
class MazeGeneration(context: Context, attrs: AttributeSet) : View(context, attrs)  {
    private val cols = 7
    private val rows = 10
    private var cellSize by Delegates.notNull<Float>()
    private var hMargin by Delegates.notNull<Float>()
    private var vMargin by Delegates.notNull<Float>()
    private var random = Random()
    private var cells =Array(7){Array(10){CellMaze(7,10)} }
    init {
        createMaze()
    }
    private val wallPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 6F // wall thickness
        color = Color.BLACK
        Log.d("bk", "wallPaint")
    }
    private fun getNeighbour(cell: CellMaze) : CellMaze?{
        val neighbours = ArrayList<CellMaze>()

        // left neighbour
        if (cell.col > 0){
            if (!cells[cell.col-1][cell.row].visited){
                neighbours.add(cells[cell.col-1][cell.row])
            }
        }
        // right neighbour
        if (cell.col < cols-1){
            if (!cells[cell.col+1][cell.row].visited){
                neighbours.add(cells[cell.col+1][cell.row])
            }
        }
        // top neighbour
        if (cell.row > 0){
            if (!cells[cell.col][cell.row-1].visited){
                neighbours.add(cells[cell.col][cell.row-1])
            }
        }
        // bottom neighbour
        if (cell.row < rows-1){
            if (!cells[cell.col][cell.row+1].visited){
                neighbours.add(cells[cell.col][cell.row+1])
            }
        }

        if (neighbours.size > 0){
            val index = random.nextInt(neighbours.size)
            return neighbours[index]
        }

        // if its neighbors are equal to zero, return null
        return null
    }
    private fun removeWall(current: CellMaze, next: CellMaze){
        // down
        if (current.col == next.col && current.row == next.row+1){
            current.topWall = false;
            next.bottomWall = false;
        }
        //down
        if (current.col == next.col && current.row == next.row-1){
            current.bottomWall = false;
            next.topWall = false;
        }
        // right
        if (current.col == next.col+1 && current.row == next.row){
            current.leftWall = false;
            next.rightWall = false;
        }
        // right
        if (current.col == next.col-1 && current.row == next.row){
            current.rightWall = false;
            next.leftWall = false;
        }
    }
    private fun createMaze() {

        /* Stack<Cell> stack = new Stack<>();
        Cell currentPosition, nextPosition;
        * */
        val stack = Stack<CellMaze>()
        var currentPosition = CellMaze(cols,rows)
        var nextPosition: CellMaze? = CellMaze(cols,rows)

        for (x in 0 until cols) {
            for (y in 0 until rows) {
                cells[x][y] = CellMaze(x,y)
            }
        }

        // Starting point
        currentPosition = cells[0][0]
        currentPosition.visited =true
        do {

            nextPosition =  getNeighbour(currentPosition)
            if (nextPosition != null){
                removeWall(currentPosition,nextPosition)
                stack.push(currentPosition)
                currentPosition = nextPosition
                currentPosition.visited = true
            }else{
                currentPosition = stack.pop()
            }

        }while (stack.isNotEmpty())


    }
    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)

        Log.d("bk", "onDraw")
        val width = width
        val height = height

        val widthHeight: Double = width.toDouble() / height.toDouble()
        val colsRows: Double = cols.toDouble() / rows.toDouble()

        cellSize = if (widthHeight < colsRows) {
            //vertical
            (width / (cols + 1)).toFloat()
        } else {
            // horizontal
            (height / (rows + 1)).toFloat()
        }

        hMargin = (width - cols * cellSize) / 2
        vMargin = (height - rows * cellSize) / 2

        canvas.translate(hMargin, vMargin)

        for (x in 0 until cols) {
            for (y in 0 until rows) {
                // draw topWall
                if (cells[x][y].topWall) {
                    canvas.drawLine(
                        x * cellSize,
                        y * cellSize,
                        (x + 1) * cellSize,
                        y * cellSize,
                        wallPaint
                    )
                }
                // draw leftWall
                if (cells[x][y].leftWall) {
                    canvas.drawLine(
                        x * cellSize,
                        y * cellSize,
                        x * cellSize,
                        (y + 1) * cellSize,
                        wallPaint
                    )
                }
                // draw bottomWall
                if (cells[x][y].bottomWall) {
                    canvas.drawLine(
                        x * cellSize,
                        (y + 1) * cellSize,
                        (x + 1) * cellSize,
                        (y + 1) * cellSize,
                        wallPaint
                    )
                }
                // draw rightWall
                if (cells[x][y].rightWall) {
                    canvas.drawLine(
                        (x + 1) * cellSize,
                        y * cellSize,
                        (x + 1) * cellSize,
                        (y + 1) * cellSize,
                        wallPaint
                    )
                }
            }
        }
    }
}