package com.example.todoappwithtdd.viewmodel.todo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoappwithtdd.di.appTestModule
import com.example.todoappwithtdd.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi // Dispatchers.setMain, resetMain 이 실험용 API 이기 때문에 이 어노세이션이 붙는다.
internal class ViewModelTest : KoinTest{
    @get:Rule
    val mockitoRule : MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var context:Application

    private val dispatcher = TestCoroutineDispatcher() //스레드 교체를 편하기위해 선언

    @Before
    fun setup(){
        startKoin {
            androidContext(context)
            modules(appTestModule)
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        stopKoin() //koin 에서 메모리에 올라왔던것을 정리
        Dispatchers.resetMain() //main Dispatchers 를 초기화 해줘야 메모리 누수가 발생하지 않는다.
    }

    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T>{
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)
        return testObserver
    }

}