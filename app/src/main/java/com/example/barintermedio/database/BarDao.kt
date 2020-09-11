package com.example.barintermedio.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneBar(mBar: Bar)

    @Update
    suspend fun updateOneBar(mBar: Bar)

    @Delete
    fun deleteOneBar(mBar: Bar)

    @Query("SELECT * FROM table_bar")
    fun geyAllBarFromDb(): LiveData<List<Bar>>

    @Query("SELECT * FROM TABLE_BAR WHERE id =:mId")
    fun getOneBarByID(mId: Int): LiveData<Bar>

    @Query("DELETE FROM table_bar")
    suspend fun  deleteAllBar()
}