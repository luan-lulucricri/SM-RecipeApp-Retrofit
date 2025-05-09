package com.example.recipeapp


data class Cat(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<CatBreed> = emptyList()
)
data class CatBreed(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String
)

typealias CatResponse = List<Cat>

