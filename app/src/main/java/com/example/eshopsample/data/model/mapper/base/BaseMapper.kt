package com.example.eshopsample.data.model.mapper.base

abstract class BaseMapper<S, R>{
    abstract fun map(source: S): R

    fun map(sourceList: List<S>): List<R> {
        val resultList = ArrayList<R>()
        for (source in sourceList) {
            resultList.add(map(source))
        }
        return resultList
    }
}