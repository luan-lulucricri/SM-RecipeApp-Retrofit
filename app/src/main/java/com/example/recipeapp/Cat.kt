package com.example.recipeapp


data class Cat(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

typealias CatResponse = List<Cat>


