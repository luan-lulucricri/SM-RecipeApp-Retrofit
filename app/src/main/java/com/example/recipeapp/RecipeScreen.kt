package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.catState
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFF8F0))) { // cor de fundo suave

        when {
            viewstate.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            viewstate.error != null -> {
                Text(
                    text = "😿 Oops! Algo deu errado...",
                    color = Color.Red,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                CatScreen(cats = viewstate.list)
            }
        }
    }
}

@Composable
fun CatScreen(cats: List<Cat>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cats) { cat ->
            CatItem(cat = cat)
        }
    }
}

@Composable
fun CatItem(cat: Cat) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(cat.url),
                contentDescription = "Imagem de gato",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(18.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "\uD83D\uDE38 Name: ${cat.breeds.get(0).name}",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color(0xFF333333)
                )
            )
            Text(
                text = "\uD83D\uDE3E Temperament: ${cat.breeds.get(0).temperament}",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color(0xFF333333)
                )
            )
        }
    }
}
