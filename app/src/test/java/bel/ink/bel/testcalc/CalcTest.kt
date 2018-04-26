package bel.ink.bel.testcalc

import bel.ink.bel.testcalc.calculator.Calc
import bel.ink.bel.testcalc.calculator.CalcImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalcTest {

	lateinit var calculator: Calc

	@Before
	@Throws(Exception::class)
	fun setUpCalc() {
		calculator = CalcImpl()
	}

	@Test
	@Throws(Exception::class)
	fun addition() = assertEquals(8f, calculator.addition(3f, 5f))

	@Test
	@Throws(Exception::class)
	fun subtraction() = assertEquals(1f, calculator.subtraction(3f, 2f))

	@Test
	@Throws(Exception::class)
	fun multiplication() {
		assertEquals(12f, calculator.multiplication(2f, 6f))
		assertEquals(0, calculator.multiplication(5f, 0f))
	}

	@Test
	@Throws(Exception::class)
	fun dividing() {
		assertEquals(2f, calculator.dividing(6f, 3f))
	}

	@Test
	@Throws(Exception::class)
	fun zeroDividing() = assertEquals(0f, calculator.dividing(6f, 3f))

	@Test(expected = IllegalArgumentException::class)
	fun testDivideByZero() = calculator.dividing(2f, 0f)

}