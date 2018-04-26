package bel.ink.bel.testcalc

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import bel.ink.bel.testcalc.calculator.Calc
import bel.ink.bel.testcalc.calculator.CalcImpl
import bel.ink.bel.testcalc.mvp.model.Database
import bel.ink.bel.testcalc.mvp.model.ModelInt
import bel.ink.bel.testcalc.mvp.model.ModelIntImpl
import bel.ink.bel.testcalc.mvp.presenter.CalcPresenterInterface
import bel.ink.bel.testcalc.mvp.presenter.CalculatorPresenterImpl
import bel.ink.bel.testcalc.util.*
import bel.ink.bel.testcalc.mvp.view.CalcView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, CalcView {

	private var isHistoryVisible = false

	private val calc: Calc by lazy {
		CalcImpl()
	}

	private val modelInt: ModelInt by lazy {
		ModelIntImpl(Room.databaseBuilder(applicationContext, Database::class.java, DB_NAME).allowMainThreadQueries().build().storeDao())
	}

	private val presenter: CalcPresenterInterface by lazy {
		CalculatorPresenterImpl(calc, modelInt)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		initListeners()
		initListView()
	}

	override fun onStart() {
		super.onStart()
		presenter.attachView(this)
	}

	override fun onStop() {
		super.onStop()
		presenter.detachView()
	}

	override fun onClick(v: View?) {
		when (v) {
			bt0 -> presenter.onClickNum(0)
			bt1 -> presenter.onClickNum(1)
			bt2 -> presenter.onClickNum(2)
			bt3 -> presenter.onClickNum(3)
			bt4 -> presenter.onClickNum(4)
			bt5 -> presenter.onClickNum(5)
			bt6 -> presenter.onClickNum(6)
			bt7 -> presenter.onClickNum(7)
			bt8 -> presenter.onClickNum(8)
			bt9 -> presenter.onClickNum(9)

			btAdd -> presenter.onClickComptutation(ADD_CODE)
			btDiv -> presenter.onClickComptutation(DIVIDE_CODE)
			btMult -> presenter.onClickComptutation(MULTIPLE_CODE)
			btSub -> presenter.onClickComptutation(SUB_CODE)

			btIs -> presenter.onClickGetResult()
			btClearLast -> presenter.onClickClearPrevious()
			btClearAll -> presenter.onClickC()
			btHistory -> presenter.onClickHistoryNotes()

		}
	}

	override fun setVisibilityOfHistory() {
		if (!isHistoryVisible) {
			frameHistory.visibility = View.VISIBLE
			makeButtonsInvisible()
			isHistoryVisible = true
		} else {
			frameHistory.visibility = View.GONE
			makeButtonsVisible()
			isHistoryVisible = false
		}
	}

	override fun printOnCalculatorDisplay(text: String) {
		calculator_display.text = text
	}

	override fun setHistoryContent(history: Array<String>) {
		list_of_history.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history)
	}

	private fun initListView() {
		list_of_history.setOnItemClickListener { _, _, position, _ ->
			presenter.onClickHistoryNotes()
			presenter.onClickHistoryOne(position)
		}
	}

	private fun initListeners() {
		bt0.setOnClickListener(this)
		bt1.setOnClickListener(this)
		bt2.setOnClickListener(this)
		bt3.setOnClickListener(this)
		bt4.setOnClickListener(this)
		bt5.setOnClickListener(this)
		bt6.setOnClickListener(this)
		bt7.setOnClickListener(this)
		bt8.setOnClickListener(this)
		bt9.setOnClickListener(this)

		btAdd.setOnClickListener(this)
		btDiv.setOnClickListener(this)
		btMult.setOnClickListener(this)
		btSub.setOnClickListener(this)

		btIs.setOnClickListener(this)
		btClearAll.setOnClickListener(this)
		btClearLast.setOnClickListener(this)
		btHistory.setOnClickListener(this)
	}

	private fun makeButtonsVisible() {
		bt0.visibility = View.VISIBLE
		bt1.visibility = View.VISIBLE
		bt2.visibility = View.VISIBLE
		bt3.visibility = View.VISIBLE
		bt4.visibility = View.VISIBLE
		bt5.visibility = View.VISIBLE
		bt6.visibility = View.VISIBLE
		bt7.visibility = View.VISIBLE
		bt8.visibility = View.VISIBLE
		bt9.visibility = View.VISIBLE

		btAdd.visibility = View.VISIBLE
		btDiv.visibility = View.VISIBLE
		btMult.visibility = View.VISIBLE
		btSub.visibility = View.VISIBLE

		btIs.visibility = View.VISIBLE
		btClearAll.visibility = View.VISIBLE
		btClearLast.visibility = View.VISIBLE

		calculator_display.visibility = View.VISIBLE
	}

	private fun makeButtonsInvisible() {
		bt0.visibility = View.GONE
		bt1.visibility = View.GONE
		bt2.visibility = View.GONE
		bt3.visibility = View.GONE
		bt4.visibility = View.GONE
		bt5.visibility = View.GONE
		bt6.visibility = View.GONE
		bt7.visibility = View.GONE
		bt8.visibility = View.GONE
		bt9.visibility = View.GONE

		btAdd.visibility = View.GONE
		btDiv.visibility = View.GONE
		btMult.visibility = View.GONE
		btSub.visibility = View.GONE

		btIs.visibility = View.GONE
		btClearAll.visibility = View.GONE
		btClearLast.visibility = View.GONE
		calculator_display.visibility = View.GONE

	}

}
