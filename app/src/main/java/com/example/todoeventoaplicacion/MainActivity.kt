package com.example.todoeventoaplicacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.todoeventoaplicacion.ui.theme.TodoEventoappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoappTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") { MainContent(navController) }
                    composable("CardDetail/{itemId}") { backStackEntry ->
                        val itemId = backStackEntry.arguments?.getString("itemId")?.toInt() ?: 0
                        val item = getItemById(itemId)
                        EventDetailScreen(
                            navController = navController,
                            title = item.title,
                            description = item.description,
                            imageUrl = item.imageUrl
                        )
                    }
                    composable("Perfil") { ProfileScreen(navController) }
                    composable("Lugares") {
                        PlacesScreen(navController)
                    }
                }

            }
        }
    }

    private fun getItemById(itemId: Int): Item {
        val items = listOf(
            Item(1, "Travis Scott", "anocido por su nombre artístico Travis Scott, es un rapero, compositor y productor musical estadounidense. ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCcxA9mCBgYHV4-CIfP23bN8Bq-ESbhftDow&s"),
            Item(2, "Bad Bunny", "Benito Antonio Martínez Ocasio, conocido artísticamente como Bad Bunny, es un cantante, compositor, productor discográfico y luchador puertorriqueño. Caracterizado por su entonación grave, se especializa en estilos musicales como reguetón y trap latino, aunque también ha interpretado otros géneros y estilos variados.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTs4HdKV3YHorbS78M6GyTZOQfBMlxvPAbY3A&s"),
            Item(3, "Lil Nas X", "Montero Lamar Hill, más conocido por su nombre artístico Lil Nas X, es un rapero, cantante y compositor estadounidense.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5yEFzDlanvMc5A6dh2ype-SkvBddr3F_W6g&s"),
            Item(4, "Drake", "Aubrey Drake Graham conocido simplemente como Drake, es un rapero, cantante, compositor, productor discográfico y actor canadiense. Siendo una figura influyente en la música popular contemporánea, Drake ha sido acreditado por popularizar el canto y la sensibilidad del R&B en el hip hop.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSX_2XsYhHIXrTtbWaXzlcQs8ooS79SuWWWAA&s")
        )
        return items.first { it.id == itemId }
    }
}

@Composable
fun MainContent(navController: NavController) {
    Column {
        AppHeader(navController)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Your Favorites",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        EventCardGrid(navController)
        Text(
            text = "All Concerts",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )
        ListConcerts() // Assuming you create a function to display concerts
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(navController: NavController) {
    TopAppBar(
        title = { Text("TodoEventos+", color = Color.White) },
        actions = {
            IconButton(onClick = { navController.navigate("Perfil") }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF6200EE)) // Purple color
    )
}

// Example function to display concerts (modify as needed)
@Composable
fun ListConcerts() {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text("Concierto 1")
        Text("Concierto 2")
        Text("Concierto 3")
        Text("Concierto 4")
        Text("Concierto 5")
    }
}


@Composable
fun EventCardGrid(navController: NavController) {
    val items = listOf(
        Item(1, "Travis Scott", "Jacques Berman Webster II, más conocido por su nombre artístico Travis Scott, es un rapero, compositor y productor musical estadounidense. ", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCcxA9mCBgYHV4-CIfP23bN8Bq-ESbhftDow&s"),
        Item(2, "Bad Bunny", "Benito Antonio Martínez Ocasio, conocido artísticamente como Bad Bunny, es un cantante, compositor, productor discográfico y luchador puertorriqueño. Caracterizado por su entonación grave, se especializa en estilos musicales como reguetón y trap latino, aunque también ha interpretado otros géneros y estilos variados.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTs4HdKV3YHorbS78M6GyTZOQfBMlxvPAbY3A&s"),
        Item(3, "Lil Nas X", "Montero Lamar Hill, más conocido por su nombre artístico Lil Nas X, es un rapero, cantante y compositor estadounidense.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5yEFzDlanvMc5A6dh2ype-SkvBddr3F_W6g&s"),
        Item(4, "Drake", "Aubrey Drake Graham conocido simplemente como Drake, es un rapero, cantante, compositor, productor discográfico y actor canadiense. Siendo una figura influyente en la música popular contemporánea, Drake ha sido acreditado por popularizar el canto y la sensibilidad del R&B en el hip hop.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSX_2XsYhHIXrTtbWaXzlcQs8ooS79SuWWWAA&s")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { item ->
            CardItem(item, navController)
        }
    }
}

@Composable
fun CardItem(item: Item, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("CardDetail/${item.id}") },
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(data = item.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

data class Item(val id: Int, val title: String, val description: String, val imageUrl: String)
