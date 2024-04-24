package com.example.roomexample2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student (
    @PrimaryKey
    val rollNo : Int,
    val name: String,
    val marks :Int,
    val grade:Char
)
