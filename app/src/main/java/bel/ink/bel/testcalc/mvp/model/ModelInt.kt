package bel.ink.bel.testcalc.mvp.model

interface ModelInt {

	fun saveComptutationHistory(computation: String)

	fun getComputationsList(): List<String>
}