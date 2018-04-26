package bel.ink.bel.testcalc

import bel.ink.bel.testcalc.mvp.model.Computation
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ModelComputation {

	lateinit var computation: Computation

	val TEST = "Test"
	val TEST_ID_0L = 0L

	@Test
	fun testCreating() {
		computation = Computation(TEST_ID_0L, TEST)
		Assert.assertNotNull(computation)
		Assert.assertEquals(TEST_ID_0L, computation.id)
		Assert.assertEquals(TEST, computation.operation)
	}


}