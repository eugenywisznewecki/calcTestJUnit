package bel.ink.bel.testcalc.mvp.presenter

interface  Presenter<in T> {
	fun detachView()
	fun attachView(view: T)

}