package com.ifansdev.listfilms.api

object SearchRepositoryProvider {

    fun provideSearchRepository() : SearchRepository {
        return SearchRepository(ApiService.create())
    }
}