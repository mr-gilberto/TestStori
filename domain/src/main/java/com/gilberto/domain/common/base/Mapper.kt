package com.gilberto.domain.common.base

interface Mapper<F, T> {

    fun mapFrom(from: F): T

}