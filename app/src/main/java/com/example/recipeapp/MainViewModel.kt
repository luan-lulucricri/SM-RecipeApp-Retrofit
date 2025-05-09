package com.example.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val _catState = mutableStateOf(RecipeState())
    val catState: State<RecipeState> = _catState

    init {
        fetchCats()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCats()
                _catState.value = _catState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _catState.value = _catState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Cat> = emptyList(),
        val error: String? = null
    )

}