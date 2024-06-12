package com.exzi.casestudy.utils

// R -> Response
// E -> Entity
interface Mapper<R, E> {
    fun map(data: R): E
}