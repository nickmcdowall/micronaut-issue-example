package com.example

import jakarta.inject.Singleton

@Singleton
class SomeService(val repository: MyRepo) {
    fun addName(name: String) {
        repository.save(MyEntity(name = name))
    }
}