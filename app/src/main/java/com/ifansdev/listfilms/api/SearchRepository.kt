package com.ifansdev.listfilms.api

import com.ifansdev.listfilms.api.dataclass.ListMovies

class SearchRepository(private val apiService: ApiService) {

    fun searchListFilms(apiKey : String) : io.reactivex.Observable<ListMovies> {
        return apiService.searchListFilms(apiKey, "ru-RU")
    }
}