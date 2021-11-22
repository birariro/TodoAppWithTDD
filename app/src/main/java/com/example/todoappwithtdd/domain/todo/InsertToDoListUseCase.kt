package com.example.todoappwithtdd.domain.todo

import com.example.todoappwithtdd.data.entity.ToDoEntity
import com.example.todoappwithtdd.data.repository.ToDoRepository
import com.example.todoappwithtdd.domain.UseCase

internal class InsertToDoListUseCase(
    private val toDoRepository: ToDoRepository
) :UseCase {
    suspend operator fun invoke(toDoList:List<ToDoEntity>){
        return toDoRepository.insertToDoList(toDoList)
    }
}