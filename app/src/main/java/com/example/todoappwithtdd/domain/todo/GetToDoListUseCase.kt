package com.example.todoappwithtdd.domain.todo

import com.example.todoappwithtdd.data.entity.ToDoEntity
import com.example.todoappwithtdd.data.repository.ToDoRepository
import com.example.todoappwithtdd.domain.UseCase

internal class GetToDoListUseCase(
    private val toDoRepository: ToDoRepository
) :UseCase {
    suspend operator fun invoke() :List<ToDoEntity>{
        return toDoRepository.getToDoList()
    }
}