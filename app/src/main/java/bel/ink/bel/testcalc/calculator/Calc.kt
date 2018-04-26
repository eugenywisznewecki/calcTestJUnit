package bel.ink.bel.testcalc.calculator

interface Calc {

	fun addition(a: Float, b: Float): Float

	fun multiplication(a: Float, b: Float): Float

	fun subtraction(a: Float, b: Float): Float

	fun dividing(a: Float, b: Float): Float

	fun computate(a: Float, b: Float, operation: (a: Float, b: Float) -> Float): Float

}