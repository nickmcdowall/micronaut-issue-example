package com.example

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import io.micronaut.serde.annotation.Serdeable

@MongoRepository
interface MyRepo : CrudRepository<MyEntity, String> {
//    @MongoFindQuery("{'name': :name}")
    fun findByName(name: String): List<MyEntity>
}

@Serdeable
@MappedEntity(value = "names")
data class MyEntity(
    @Id
    @GeneratedValue
    val id: String? = null,
    
    val name: String
)