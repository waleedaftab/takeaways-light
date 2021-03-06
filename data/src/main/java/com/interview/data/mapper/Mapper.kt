package com.interview.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}