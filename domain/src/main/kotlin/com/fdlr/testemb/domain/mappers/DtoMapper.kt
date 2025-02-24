package com.fdlr.testemb.domain.mappers

interface DtoMapper<in T, out Model> {
    fun fromDto(from: T): Model
    fun fromDto(from: List<T>): List<Model> = from.map { fromDto(it) }
}