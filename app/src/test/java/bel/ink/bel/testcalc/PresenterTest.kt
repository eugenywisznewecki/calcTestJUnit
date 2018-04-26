package bel.ink.bel.testcalc

import bel.ink.bel.testcalc.calculator.Calc
import bel.ink.bel.testcalc.calculator.CalcImpl
import bel.ink.bel.testcalc.mvp.model.ModelInt
import bel.ink.bel.testcalc.mvp.model.ModelIntImpl
import bel.ink.bel.testcalc.mvp.presenter.CalculatorPresenterImpl
import bel.ink.bel.testcalc.mvp.view.CalcView
import bel.ink.bel.testcalc.util.ADD_CODE
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PresenterTest {

	private lateinit var presenter: CalculatorPresenterImpl

	@Mock
	private lateinit var mockedCalculator: Calc
	@Mock
	private lateinit var mockedStore: ModelInt
	@Mock
	private lateinit var mockedView: CalcView

	val TEST = "Test"
	val POSITION_FOR_TEST = 0


	@Before
	fun setup() {
		initMocks(this)
		mockedCalculator = Mockito.mock(CalcImpl::class.java)
		mockedStore = Mockito.mock(ModelIntImpl::class.java)
		mockedView = Mockito.mock(CalcView::class.java)
		presenter = CalculatorPresenterImpl(mockedCalculator, mockedStore)
	}

	@Test
	@Throws(Exception::class)
	fun getFromDBTest() {
		`when`(mockedStore.getComputationsList()).thenReturn(arrayListOf(TEST))
		presenter.apply {
			attachView(mockedView)
			onClickHistoryNotes()
			onClickHistoryOne(POSITION_FOR_TEST)
		}

		verify(mockedView).printOnCalculatorDisplay(ArgumentMatchers.anyString())
	}

	@Test
	@Throws(Exception::class)
	fun calculatorOperationsTest() {
		presenter.apply {
			onClickNum(1)
			onClickComptutation(ADD_CODE)
			onClickNum(1)
			onClickGetResult()
		}
		verify(mockedCalculator).addition(ArgumentMatchers.anyFloat(), ArgumentMatchers.anyFloat())
		presenter.onClickC()
	}


	@Test
	@Throws(Exception::class)
	fun viewAttachDetachTest() {
		presenter.apply {
			view = null
			attachView(mockedView)
			assertNotNull(view)
			detachView()
		}
		assertEquals(null, presenter.view)
	}


	@Test
	@Throws(Exception::class)
	fun savingInStoreTest() {
		presenter.apply {
			onClickNum(2)
			onClickComptutation(ADD_CODE)
			onClickNum(2)
			onClickGetResult()
		}
		verify(mockedStore).saveComptutationHistory(ArgumentMatchers.anyString())
	}
}