package com.example.todoappwithtdd.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoappwithtdd.data.entity.ToDoEntity
import com.example.todoappwithtdd.domain.todo.GetToDoListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/*
필요한 useCase
1. GetToDoListUseCase
2. UpdateToDoUseCase
3. DeleteAllTodoItemUseCase
 */
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase
) : ViewModel()
{
    private val _toDoListLiveData = MutableLiveData<List<ToDoEntity>>()
    val todoListLiveData : LiveData<List<ToDoEntity>> = _toDoListLiveData

    fun fetchData() : Job = viewModelScope.launch{
        _toDoListLiveData.postValue(getToDoListUseCase())
    }
}