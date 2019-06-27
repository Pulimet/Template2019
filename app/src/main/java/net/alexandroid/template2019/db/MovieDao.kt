package net.alexandroid.template2019.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.alexandroid.template2019.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * from movies")
    fun getMovies(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)


    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}