package com.example.panzehir.view_Patient.games.sudoku

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
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
import androidx.navigation.Navigation
import com.example.panzehir.R
import com.example.panzehir.databinding.SudokuFragmentBinding
import com.example.panzehir.viewModelPatient.gamesViewModel.SudokuViewModel
import com.example.panzehir.view_Patient.HostFragment2
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
     /*   val builder = Dialog(this.context!!)
        builder.setContentView(R.layout.startgame_and_howtoplay)
        builder.show()*/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Oyuna başla")
        builder.setPositiveButton("Başla") { dialog, which ->
          builder.setCancelable(true)
            binding.chronomether.start()
        }
        builder.show()

        binding.playtimeButton.setOnClickListener{
            binding.chronomether.start()
        }
        binding.stopTimebutton.setOnClickListener{
            binding.chronomether.stop()
        }

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

        viewModel.sudokuGame.wrongLiveData.observe(this, Observer { Wrong(it) })
        //delete
        binding.deleteButton.setOnClickListener { viewModel.sudokuGame.delete() }
        binding.solveButton.setOnClickListener {
            viewModel.sudokuGame.solve()
            binding.chronomether.stop()
        }
        binding.NewGamebutton.setOnClickListener{
            viewModel.sudokuGame.newGame()
            binding.chronomether.setBase(SystemClock.elapsedRealtime())
            binding.chronomether.start()
        }

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
    private fun Wrong(isWrong: Boolean?) = isWrong?.let {
        //Şevvoş Buası yanlış kutuyu güzel mavi renkte yapacak
        val color = if (it) ContextCompat.getColor(this.context!!, R.color.teal_700) else Color.LTGRAY
        binding.notesButton.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)

    }
    private val selectedCellPaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#6ead3a")
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



}