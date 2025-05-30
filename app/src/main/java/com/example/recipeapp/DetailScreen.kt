package com.example.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(cat: Cat, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Gato") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(cat.url),
                contentDescription = "Cat Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (cat.breeds.isNotEmpty()) {
                val breed = cat.breeds[0]
                Text("Name: ${breed.name}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("Origin: ${breed.origin}", fontSize = 16.sp)
                Text("Temperament: ${breed.temperament}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Description:", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Text(breed.description, fontSize = 14.sp, color = Color.Gray)
            } else {
                Text("No breed information available.", fontSize = 14.sp)
            }
        }
    }
}
