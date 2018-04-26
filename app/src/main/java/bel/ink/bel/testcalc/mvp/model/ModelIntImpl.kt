package bel.ink.bel.testcalc.mvp.model


open class ModelIntImpl(val storeDao: StoreDao) : ModelInt {

	override fun saveComptutationHistory(computation: String) {
		storeDao.insert(Computation(0, computation))
	}

	override fun getComputationsList() = storeDao.all.map { it.operation }
}