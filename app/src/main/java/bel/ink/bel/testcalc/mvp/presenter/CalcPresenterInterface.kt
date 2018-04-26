package bel.ink.bel.testcalc.mvp.presenter

import bel.ink.bel.testcalc.mvp.view.CalcView

interface CalcPresenterInterface : Presenter<CalcView> {

	fun onClickNum(num: Int)

	fun onClickComptutation(comp: Int)

	fun onClickGetResult()

	fun onClickC()

	fun onClickClearPrevious()

	fun onClickHistoryNotes()

	fun onClickHistoryOne(pos: Int)
}