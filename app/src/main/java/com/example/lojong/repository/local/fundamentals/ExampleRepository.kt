package com.example.lojong.repository.local.fundamentals

interface ExampleRepository {
    suspend fun getExample(): ExampleData
}