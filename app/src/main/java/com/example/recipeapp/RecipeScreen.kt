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
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import android.net.Uri
import com.google.gson.Gson
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.catState
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF8F0))) {
        when {
            viewstate.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            viewstate.error != null -> Text(
                text = "😿 Oops! Algo deu errado...",
                color = Color.Red,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier.align(Alignment.Center)
            )
            else -> CatScreen(viewstate.list, navController)
        }
    }
}

@Composable
fun CatScreen(cats: List<Cat>, navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(cats) { cat ->
            CatItem(cat = cat, onClick = {
                val catJson = Uri.encode(Gson().toJson(cat))
                navController.navigate("detail/$catJson")
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatItem(cat: Cat, onClick: () -> Unit) {
    Card(
        onClick = onClick,
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

            if (cat.breeds.isNotEmpty()) {
                Text("🐱 Name: ${cat.breeds[0].name}", fontSize = 12.sp)
                Text("😺 Temperament: ${cat.breeds[0].temperament}", fontSize = 12.sp)
            }
        }
    }
}

