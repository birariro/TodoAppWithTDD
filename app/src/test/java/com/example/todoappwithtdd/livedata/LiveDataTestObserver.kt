package com.example.todoappwithtdd.livedata

import androidx.lifecycle.Observer
import java.lang.AssertionError


//test 에서 liveData를 사용할수없기에 비슷한 기능의 유틸성 클래스
class LiveDataTestObserver<T>: Observer<T> {

    private val value : MutableList<T> = mutableListOf()
    override fun onChanged(t: T) {
        value.add(t)
    }

    fun assertValueSequence(sequence : List<T>): LiveDataTestObserver<T>{
        var i = 0;
        val actualIterator = value.iterator()
        val expectedIterator = sequence.iterator()

        var actualNext :Boolean
        var expectedNext : Boolean
        while (true){
            actualNext = actualIterator.hasNext()
            expectedNext = expectedIterator.hasNext()

            if(!actualNext || !expectedNext) break

            var actual : T = actualIterator.next()
            var expected :T = expectedIterator.next()

            if(actual != expected){
                throw AssertionError("actual : ${actual}, expected : ${expected}, index: $i")
            }
        }
        i++
        if(actualNext){
            throw AssertionError("More values received than expected ($i)")
        }
        if(expectedNext){
            throw AssertionError("Fewer values received than expected ($i)")
        }
        return this
    }

}