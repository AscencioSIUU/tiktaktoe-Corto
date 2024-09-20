package com.example.tiktaktoe.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tiktaktoe.models.CellModel

fun getCells(size: Int) = List(size * size){CellModel()}

class BoardViewModel(size: Int, initialPlayer: Int) {
    private val diagonalStepNegative = size + 1;
    private val diagonalStepPositive = size - 1;
    private val horizontalStep = size - 2;
    private val verticalStep = size;
    private val N = size * size;


    private val _cells = getCells(size).toMutableStateList()
    private var _gameStatus by mutableStateOf(0)
    private var _player by mutableStateOf(initialPlayer)

    val cells: List<CellModel>
        get() = _cells

    val gameStatus: Int
        get() = _gameStatus

    val player: Int
        get() = _player


    fun alternatePlayer(){
        if(_player == 1){
            _player = 2
        }else
        {
            _player = 1
        }
    }

    fun checkAllCellDiagonals(cell: CellModel) {
        val index = _cells.indexOf(cell);

        if (index - diagonalStepNegative >= 0) {
            if (_cells[index - diagonalStepNegative].state == cell.state) {
                checkSingleStep(
                    _cells[index - diagonalStepNegative],
                    -diagonalStepNegative
                )
            }
        }
        if (index + diagonalStepNegative < N) {
            if (_cells[index + diagonalStepNegative].state == cell.state) {
                checkSingleStep(
                    _cells[index + diagonalStepNegative],
                    diagonalStepNegative
                )
            }
        }
        if (index - diagonalStepPositive >= 0) {
            if (_cells[index - diagonalStepPositive].state == cell.state) {
                checkSingleStep(
                    _cells[index - diagonalStepPositive],
                    -diagonalStepPositive
                )
            }
        }
        if (index + diagonalStepPositive < N) {
            if (_cells[index + diagonalStepPositive].state == cell.state) {
                checkSingleStep(
                    _cells[index + diagonalStepPositive],
                    diagonalStepPositive
                )
            }
        }

    }

    fun checkSingleStep(cell: CellModel, previousStep: Int) {
        val index = _cells.indexOf(cell)
        if ((index + previousStep >= 0) && (index + previousStep < N)) {
            if (_cells[index + previousStep].state == cell.state) {
                _gameStatus = cell.state
            }
        }
    }

    fun checkAllCellCrosses(cell: CellModel){
        val index = _cells.indexOf(cell);

        if (index - horizontalStep >= 0) {
            if (_cells[index - horizontalStep].state == cell.state) {
                checkSingleStep(_cells[index - horizontalStep], -horizontalStep)

            }
        }
        if (index + horizontalStep < N) {
            if (_cells[index + horizontalStep].state == cell.state) {
                checkSingleStep(_cells[index + horizontalStep], horizontalStep)
            }
        }
        if (index - verticalStep >= 0) {
            if (_cells[index - verticalStep].state == cell.state) {
                checkSingleStep(_cells[index - verticalStep], -verticalStep)
            }
        }
        if (index + verticalStep < N) {
            if (_cells[index + verticalStep].state == cell.state) {
                return checkSingleStep(_cells[index + verticalStep], verticalStep)
            }
        }
    }

    fun changeState(cell: CellModel, newState: Int) {
        cell.changeState(newState)

        checkAllCellDiagonals(cell)
        checkAllCellCrosses(cell)

        alternatePlayer()
    }

    fun getCell(i: Int): CellModel{
        return _cells[i]
    }
}