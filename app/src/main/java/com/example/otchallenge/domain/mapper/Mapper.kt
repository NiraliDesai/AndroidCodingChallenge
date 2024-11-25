package com.example.otchallenge.domain.mapper

/**
 * Interface for defining mapper functionality
 */
interface Mapper<From, To> {
    fun mapFrom(from: From): To
}