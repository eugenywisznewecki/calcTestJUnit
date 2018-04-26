package bel.ink.bel.testcalc.mvp.view

interface CalcView {

	fun setVisibilityOfHistory()

	fun printOnCalculatorDisplay(text: String)

	fun setHistoryContent(history: Array<String>)


}