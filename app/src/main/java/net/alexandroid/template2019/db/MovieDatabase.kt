package net.alexandroid.template2019.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.alexandroid.template2019.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}