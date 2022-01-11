package com.example.panzehir.view_Patient.games.sudoku

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.panzehir.R
import com.example.panzehir.databinding.SudokuFragmentBinding
import com.example.panzehir.viewModelPatient.gamesViewModel.SudokuViewModel
import com.example.panzehir.view_Patient.games.sudoku.canvas.SudokuBoardView
import com.example.panzehir.view_Patient.games.sudoku.game.Cell


class Sudoku : Fragment() , SudokuBoardView.OnTouchListener{

    private var _binding: SudokuFragmentBinding?=null
    private val binding get() = _binding!!
    private lateinit var numberButtons: List<Button>
    private  val viewModel: SudokuViewModel by lazy {
        ViewModelProvider(this)[SudokuViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=SudokuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Oyuna başla")
        builder.setPositiveButton("Başla") { dialog, which ->
          builder.setCancelable(true)
            setChronomether()
        }
        builder.show()
        binding.chronomether.format=" %s"
        binding.sudokuBoardView.registerListener(this)
        viewModel.sudokuGame.selectedCellLiveData.observe(this, Observer { updateSelectedCellUI(it) })
        viewModel.sudokuGame.cellsLiveData.observe(this, Observer { updateCells(it) })
        numberButtons = listOf(
            binding.oneButton,
            binding.twoButton,
            binding.threeButton,
            binding.fourButton,
            binding.fiveButton,
            binding.sixButton,
            binding.sevenButton,
            binding.eightButton,
            binding.nineButton
        )
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {

                viewModel.sudokuGame.handleInput(
                    index + 1
                )
            }
        }
        // Edit the pen, click
        viewModel.sudokuGame.isTakingNotesLiveData.observe(this, Observer { updateNoteTakingUI(it) })
        viewModel.sudokuGame.highlightedKeysLiveData.observe(this, Observer { updateHighlightedKeys(it) })
        binding.notesButton.setOnClickListener { viewModel.sudokuGame.changeNoteTakingState() }
        //delete
        binding.deleteButton.setOnClickListener { viewModel.sudokuGame.delete() }
        binding.solveButton.setOnClickListener {
            viewModel.sudokuGame.solve()
            binding.chronomether.stop()
            val time= binding.chronomether.text
            //Bu toast mesajını ortalayabilir miyiz???
            Toast.makeText(this.context,"${time} süresinde bulmacayı çözdünüz.",Toast.LENGTH_LONG).show()


        }
        binding.NewGamebutton.setOnClickListener{
            viewModel.sudokuGame.newGame(30)
            setChronomether()
        }
        binding.EasyLevel.setOnClickListener {
            setChronomether()
            viewModel.sudokuGame.newGame(30) }
        binding.MediumLevel.setOnClickListener {
            setChronomether()
            viewModel.sudokuGame.newGame(40)  }
        binding.HardLevel.setOnClickListener {
            setChronomether()
            viewModel.sudokuGame.newGame(50)  }

    }
    private fun setChronomether(){
        binding.chronomether.setBase(SystemClock.elapsedRealtime())
        binding.chronomether.start()
    }
    private fun updateCells(cells: List<Cell>?) = cells?.let {
        binding.sudokuBoardView.updateCells(cells)
    }

    private fun updateSelectedCellUI(cell: Pair<Int, Int>?) = cell?.let {
        binding.sudokuBoardView.updateSelectedCellUI(cell.first, cell.second)
    }

    private fun updateNoteTakingUI(isNoteTaking: Boolean?) = isNoteTaking?.let {
        val color = if (it) ContextCompat.getColor(this.context!!, R.color.purple_200) else Color.LTGRAY
        binding.notesButton.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }


    private fun updateHighlightedKeys(set: Set<Int>?) = set?.let {
        numberButtons.forEachIndexed { index, button ->
            val color = if (set.contains(index + 1)) ContextCompat.getColor(this.context!!, R.color.purple_200) else Color.LTGRAY
            button.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        }
    }

    override fun onCellTouched(row: Int, col: Int) {
        viewModel.sudokuGame.updateSelectedCell(row, col)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}