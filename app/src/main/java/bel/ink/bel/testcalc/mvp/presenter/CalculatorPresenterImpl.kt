package bel.ink.bel.testcalc.mvp.presenter

import bel.ink.bel.testcalc.calculator.Calc
import bel.ink.bel.testcalc.mvp.model.ModelInt
import bel.ink.bel.testcalc.util.*
import bel.ink.bel.testcalc.mvp.view.CalcView


class CalculatorPresenterImpl(private val calc: Calc, private val modelInt: ModelInt) : CalcPresenterInterface {

	private var operatationsHistory = emptyArray<String>()
	private var firstString = ""
	private var secondString = ""
	private var firstNum: Float = 0f
	private var secondNum: Float = 0f
	private var resultString = ""
	private var resultNumber = 0f

	private var operationCode: Int = NO_OPERATION

	private var computatedString = ""
	private val operators = arrayOf(PLUS, MINUS, MULTIPLE, DIVIDE)


	var view: CalcView? = null

	override fun attachView(view: CalcView) {
		this.view = view
	}

	override fun detachView() {
		view = null
	}


	override fun onClickNum(num: Int) {
		if (resultString.isEmpty()) {
			computatedString += num
			if (operationCode == NO_OPERATION) {
				firstString = computatedString
				view?.printOnCalculatorDisplay(firstString)
			} else {
				secondString = computatedString
				view?.printOnCalculatorDisplay("$firstString ${operators[operationCode]} $secondString")
			}
		}
	}


	override fun onClickGetResult() {
		var res = NOTHING
		if (operationCode != NO_OPERATION && !secondString.isEmpty()) {
			firstNum = firstString.toFloat()
			secondNum = secondString.toFloat()
			when (operationCode) {
				ADD_CODE -> resultNumber = calc.addition(firstNum, secondNum)
				SUB_CODE -> resultNumber = calc.subtraction(firstNum, secondNum)
				MULTIPLE_CODE -> resultNumber = calc.multiplication(firstNum, secondNum)
				DIVIDE_CODE -> {
					if (secondNum == 0f) res = ZERO_COMP
					else resultNumber = calc.dividing(firstNum, secondNum)
				}
			}
			if (secondNum != 0f) res = "$firstString ${operators[operationCode]} $secondString = $resultNumber"
			view?.printOnCalculatorDisplay(res)
			modelInt.saveComptutationHistory(res)
			resultString = resultNumber.toString()
		}
	}

	override fun onClickC() {
		computatedString = NOTHING
		firstString = NOTHING
		secondString = NOTHING
		resultString = NOTHING
		operationCode = NO_OPERATION
		view?.printOnCalculatorDisplay(NOTHING)
	}

	override fun onClickComptutation(comp: Int) {
		if (resultString.isEmpty() && secondString.isEmpty()) {
			if (!firstString.isEmpty()) {
				operationCode = comp
				computatedString = NOTHING
				view?.printOnCalculatorDisplay("$firstString ${operators[operationCode]}")
			}
		}
	}


	override fun onClickHistoryNotes() {
		view?.setVisibilityOfHistory()
		operatationsHistory = modelInt.getComputationsList().toTypedArray()
		operatationsHistory.reverse()
		view?.setHistoryContent(operatationsHistory)
	}

	override fun onClickHistoryOne(position: Int) {
		view?.printOnCalculatorDisplay(operatationsHistory[position])
	}

	override fun onClickClearPrevious() {
		if (resultString.isEmpty()) {
			when (true) {
				!secondString.isEmpty() -> {
					computatedString = computatedString.substring(0, computatedString.length - 1)
					if (computatedString.isEmpty()) {
						secondString = NOTHING
						view?.printOnCalculatorDisplay("$firstString ${operators[operationCode]}")
					} else {
						secondString = computatedString
						view?.printOnCalculatorDisplay("$firstString ${operators[operationCode]} $secondString")
					}
				}
				operationCode != NO_OPERATION -> {
					operationCode = NO_OPERATION
					computatedString = firstString
					view?.printOnCalculatorDisplay(firstString)
				}
				!firstString.isEmpty() -> {
					computatedString = computatedString.substring(0, computatedString.length - 1)
					if (computatedString.isEmpty()) {
						firstString = NOTHING
						view?.printOnCalculatorDisplay(NOTHING)
					} else {
						firstString = computatedString
						view?.printOnCalculatorDisplay(firstString)
					}
				}
			}
		}
	}

}