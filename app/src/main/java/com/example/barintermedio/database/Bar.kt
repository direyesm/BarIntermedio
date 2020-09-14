package com.example.barintermedio.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "table_bar")
data class Bar (
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Int = 0,
    val name: String,
    var precio: Int = 0,
    var cantidad: Int = 0)
