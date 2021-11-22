package com.example.todoappwithtdd.viewmodel.todo

import com.example.todoappwithtdd.data.entity.ToDoEntity
import com.example.todoappwithtdd.domain.todo.InsertToDoListUseCase
import com.example.todoappwithtdd.presentation.list.ListViewModel
import com.example.todoappwithtdd.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.inject

/*
[ListViewModel] 을 테스트 하기위한 유닛테스트 클래스
1. initData
2. test viewModel fetch
3. test item update
4. test item delete all
 */
@ExperimentalCoroutinesApi
internal class ListViewModelTest: ViewModelTest() {

    private val viewModel : ListViewModel by inject()
    private val insertToDoListUseCase : InsertToDoListUseCase by inject()

    private val mockList = (0 until 10).map{
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }
    /*
    유스케이스
    1. insertTodoListUseCase
    2. GetToDoItemUseCase
     */

    @Before
    fun init(){
        initData()
    }
    private fun initData() = runBlockingTest {
        insertToDoListUseCase(mockList)
    }

    //Test : 입력된 데이터를 불러와서 검증한다.
    @Test
    fun `test viewModel fetch`() :Unit = runBlockingTest {
        val testObservable = viewModel.todoListLiveData.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                mockList
            )
        )
    }
}