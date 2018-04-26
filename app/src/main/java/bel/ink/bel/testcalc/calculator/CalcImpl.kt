package bel.ink.bel.testcalc.calculator

open class CalcImpl : Calc {

	override fun addition(a: Float, b: Float) = a + b

	override fun subtraction(a: Float, b: Float) = a - b

	override fun multiplication(a: Float, b: Float) = a * b

	override fun dividing(a: Float, b: Float): Float {
		require(b.isFinite())
		require(b != 0F) ///
		return a / b
	}

	override fun computate(a: Float, b: Float, computateIn: (a: Float, b: Float) -> Float) = computateIn(a, b)

}