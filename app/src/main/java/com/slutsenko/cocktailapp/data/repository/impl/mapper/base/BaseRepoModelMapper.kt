package com.slutsenko.cocktailapp.data.repository.impl.mapper.base

abstract class BaseRepoModelMapper<RepoModel, DbModel, NetModel> {

    open fun mapDbToRepo(db: DbModel): RepoModel {
        throw NotImplementedError("provide mapping for model")
    }

    open fun mapRepoToDb(repo: RepoModel): DbModel {
        throw NotImplementedError("provide mapping for model")
    }

    open fun mapNetToRepo(net: NetModel): RepoModel {
        throw NotImplementedError("provide mapping for model")
    }

    open fun mapDbToRepo(db: List<DbModel>): List<RepoModel> = db.map(::mapDbToRepo)
    open fun mapRepoToDb(repo: List<RepoModel>): List<DbModel> = repo.map(::mapRepoToDb)
    open fun mapNetToRepo(net: List<NetModel>): List<RepoModel> = net.map(::mapNetToRepo)
}