package com.example.barintermedio

import androidx.lifecycle.LiveData
import com.example.barintermedio.database.Bar
import com.example.barintermedio.database.BarDao

class BarRepository(private val mBarDao: BarDao) {

    val listAllBar : LiveData<List<Bar>> = mBarDao.geyAllBarFromDb()

    suspend fun insertBar(mBar: Bar){
        mBarDao.insertOneBar(mBar)
    }

    suspend fun deleteAll(){
        mBarDao.deleteAllBar()
    }

    fun getOneBarById(id: Int) : LiveData<Bar>{
        return mBarDao.getOneBarByID(id)
    }

    suspend fun updateBar(mBar: Bar){
        mBarDao.updateOneBar(mBar)
    }
}