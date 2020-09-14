package com.example.barintermedio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.barintermedio.database.Bar
import com.example.barintermedio.database.BarDao
import com.example.barintermedio.database.BarDataBase
import kotlinx.coroutines.launch

class BarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BarRepository
    val allBar: LiveData<List<Bar>>

    init {
        val barDao = BarDataBase.getDatabase(application).getBarDao()
        repository = BarRepository(barDao)
        allBar = repository.listAllBar
    }

    fun insertBar(bar: Bar) = viewModelScope.launch {
        repository.insertBar(bar)
    }

    fun deleteAllBar() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getOnBarByID(id : Int): LiveData<Bar>{
        return repository.getOneBarById(id)
    }

    fun updateBar(mBar: Bar)  = viewModelScope.launch {
        repository.updateBar(mBar)
    }

}