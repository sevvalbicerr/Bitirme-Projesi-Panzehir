package com.example.panzehir.view_Patient.games.mazegame

import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.panzehir.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.properties.Delegates
class MazeGeneration(context: Context, attrs: AttributeSet) : View(context, attrs)  {
    private val cols = 10
    private val rows = 10
    private var cellSize by Delegates.notNull<Float>()
    private var hMargin by Delegates.notNull<Float>()
    private var vMargin by Delegates.notNull<Float>()
    private var random = Random()
    private var cells =Array(10){Array(10){CellMaze(10,10)} }


    private var player = CellMaze(cols,rows)
    private var exit = CellMaze(cols,rows)

    private enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    init {
        createMaze()
    }
    private val wallPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 6F // wall thickness
        color = Color.BLACK
        Log.d("bk", "wallPaint")
    }
    private val cellPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 6F // wall thickness
        color = Color.YELLOW
        Log.d("bk", "wallPaint")
    }
    private val playerPaint = Paint().apply {
        color = Color.RED
    }
    private val exitPaint = Paint().apply {
        color = Color.BLUE
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
        val stack = Stack<CellMaze>()
        var currentPosition = CellMaze(cols,rows)
        var nextPosition: CellMaze? = CellMaze(cols,rows)
        for (x in 0 until cols) {
            for (y in 0 until rows) {
                cells[x][y] = CellMaze(x,y)
            }
        }
        player = cells[0][0]
        exit = cells[cols-1][rows-1]
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

        // add the small margin wall
        val margin : Float = cellSize.toFloat()/10


        canvas.drawRect(
            player.col*cellSize+margin,
            player.row*cellSize+margin,
            (player.col+1)*cellSize-margin,
            (player.row+1)*cellSize-margin,
            playerPaint
        )
        canvas.drawRect(
            exit.col*cellSize+margin,
            exit.row*cellSize+margin,
            (exit.col+1)*cellSize-margin,
            (exit.row+1)*cellSize-margin,
            exitPaint
        )

    }
    private fun movePlayer(direction: Direction){
        when(direction){
            Direction.UP ->
                if (!player.topWall){
                    player = cells[player.col][player.row-1]

                }
            Direction.DOWN ->
                if (!player.bottomWall){
                    player = cells[player.col][player.row+1]
                }
            Direction.LEFT ->
                if (!player.leftWall){
                    player = cells[player.col-1][player.row]
                }
            Direction.RIGHT ->
                if (!player.rightWall){
                    player = cells[player.col+1][player.row]
                }
        }
        checkExit()
        invalidate()
    }
    //control finish then newGame (PROBLEMMMM)
    private fun checkExit(){

        if (player == exit){
            findNavController().navigate(R.id.action_mazeGame_to_games2)
            //createMaze()
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.getAction() == MotionEvent.ACTION_DOWN) return true
        if (event.action == MotionEvent.ACTION_MOVE){
            var x : Float = event.x.toFloat()
            var y : Float = event.y.toFloat()

            var playerCenterX : Float = hMargin + (player.col.toFloat() + 0.5f)*cellSize
            var playerCenterY : Float = vMargin + (player.row.toFloat() + 0.5f)*cellSize

            //negative
            var dx : Float = x - playerCenterX as Float
            var dy : Float = y - playerCenterY as Float

            // absolute
            var absDx : Float = abs(dx)
            var absDy : Float = abs(dy)

            if (absDx > cellSize || absDy > cellSize){
                // move in x-direction
                if (absDx > absDy){
                    if (dx > 0) { //move to the right
                        movePlayer(Direction.RIGHT)

                    }else{ // move to the left
                        movePlayer(Direction.LEFT)
                    }
                }
                // move in y-direction
                else{
                    if (dy > 0){ // move to the down
                        movePlayer(Direction.DOWN)
                    } else { // move to the up
                        movePlayer(Direction.UP)
                    }
                }
            }
            return true
        }

        return super.onTouchEvent(event)
    }
}