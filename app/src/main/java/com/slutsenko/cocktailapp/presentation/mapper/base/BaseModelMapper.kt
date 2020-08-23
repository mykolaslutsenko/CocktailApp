package com.slutsenko.cocktailapp.presentation.mapper.base

abstract class BaseModelMapper<OutModel : Any, InModel : Any> {

    open fun mapTo(model: InModel): OutModel =
        throw NotImplementedError("provide mapping for model")

    open fun mapFrom(model: OutModel): InModel =
        throw NotImplementedError("provide mapping for model")

    fun mapTo(model: List<InModel>): List<OutModel> = model.map { mapTo(it) }
    fun mapFrom(model: List<OutModel>): List<InModel> = model.map { mapFrom(it) }
}