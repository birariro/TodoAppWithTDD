package com.example.todoappwithtdd.data.entity

data class ToDoEntity (
    var id: Long =0,
    var title :String,
    var description :String,
    val hasCompleted : Boolean =false

        )
