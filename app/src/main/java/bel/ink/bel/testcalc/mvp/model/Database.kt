package bel.ink.bel.testcalc.mvp.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Computation::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

	abstract fun storeDao(): StoreDao
}