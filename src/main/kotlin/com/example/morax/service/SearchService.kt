package com.example.morax.service

import com.example.morax.model.SearchResp

interface SearchService {
    fun searchData(searchText: String): SearchResp
}