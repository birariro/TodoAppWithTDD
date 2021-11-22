package com.example.todoappwithtdd.di

import com.example.todoappwithtdd.data.repository.TestToDoRepository
import com.example.todoappwithtdd.data.repository.ToDoRepository
import com.example.todoappwithtdd.domain.todo.GetToDoListUseCase
import com.example.todoappwithtdd.domain.todo.InsertToDoListUseCase
import com.example.todoappwithtdd.presentation.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    //viewModel
    viewModel { ListViewModel(get()) }

    //UseCase
    factory {  GetToDoListUseCase(get()) }
    factory {  InsertToDoListUseCase(get()) }
    //Repository
    single<ToDoRepository> { TestToDoRepository() }
}