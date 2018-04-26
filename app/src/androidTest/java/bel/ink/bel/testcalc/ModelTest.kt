package bel.ink.bel.testcalc

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import bel.ink.bel.testcalc.mvp.model.Computation
import bel.ink.bel.testcalc.mvp.model.Database
import bel.ink.bel.testcalc.mvp.model.StoreDao
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModelTest {

	private lateinit var db: Database
	private lateinit var storeDao: StoreDao

	@Before
	@Throws(Exception::class)
	fun createDb() {
		db = Room.inMemoryDatabaseBuilder(
				InstrumentationRegistry.getContext(),
				Database::class.java)
				.build()
		storeDao = db.storeDao()
	}

	@Test
	@Throws(Exception::class)
	fun pasteoperationReadTest() {

		val operationToInsert = Computation(0, "test")
		storeDao.insert(operationToInsert)
		val operationsListPostInsert = storeDao.all
		assertEquals(1, operationsListPostInsert.size)
		assertTrue(operationsListPostInsert[0].operation == "test")
	}

	@After
	@Throws(Exception::class)
	fun closeDb() {
		db.close()
	}


}