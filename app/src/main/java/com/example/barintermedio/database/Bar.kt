package com.example.barintermedio.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "table_bar")
data class Bar (
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Int = 0,
    val name: String,
    val price: Int,
    val unit: Int,
    val total: Int)