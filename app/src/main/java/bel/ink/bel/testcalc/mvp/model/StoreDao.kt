package bel.ink.bel.testcalc.mvp.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface StoreDao {

	@get:Query("SELECT * FROM computations")
	val all: List<Computation>

	@Insert
	fun insert(computation: Computation)

}