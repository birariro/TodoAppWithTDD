package com.example.todoappwithtdd.data.repository

import com.example.todoappwithtdd.data.entity.ToDoEntity

class TestToDoRepository : ToDoRepository {
    //테스트 용이다.
    private val toDoList : MutableList<ToDoEntity> = mutableListOf()
    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.toDoList.addAll(toDoList)
    }
}