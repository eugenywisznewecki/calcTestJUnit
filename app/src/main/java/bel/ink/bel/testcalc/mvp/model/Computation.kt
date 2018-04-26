package bel.ink.bel.testcalc.mvp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "computations")
data class Computation(
		@PrimaryKey(autoGenerate = true)
		var id: Long = 0,
		var operation: String = ""
)