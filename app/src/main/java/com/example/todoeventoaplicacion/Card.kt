package com.example.todoeventoaplicacion

import android.annotation.SuppressLint

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.todoeventoaplicacion.ui.theme.TodoEventoappTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    navController: NavController,
    title: String,
    description: String,
    imageUrl: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Evento", color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF6200EE)),
                actions = {
                    IconButton(onClick = { navController.navigate("main") }) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = Color.White)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Imagen del Evento",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = title, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp))

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "About", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Placeholder for Favorite action */ },
                    modifier = Modifier.weight(1f).padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB86FC))
                ) {
                    Text("Favorite", color = Color.White)
                }
                Button(
                    onClick = { navController.navigate("Lugares") }, // Navigate to Lugares
                    modifier = Modifier.weight(1f).padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBB86FC))
                ) {
                    Text("Buy", color = Color.White)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCardScreen() {
    TodoEventoappTheme {
        EventDetailScreen(
            navController = rememberNavController(),
            title = "Title",
            description = "This is the description of the event",
            imageUrl = "https://unamglobal.unam.mx/wp-content/uploads/2022/04/conciertos-1-1.jpg"
        )
    }
}
