    package com.example.todoeventoaplicacion

    import android.annotation.SuppressLint
    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.layout.*
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.AccountCircle
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material.icons.filled.ArrowForward
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.ListItem
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBar
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavController
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.rememberNavController
    import com.example.todoeventoaplicacion.ui.theme.TodoEventoappTheme

    class Lugares(private val navController: NavHostController) : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                TodoEventoappTheme {
                    PlacesScreen(navController)
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PlacesScreen(navController: NavController) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lugares de Conciertos") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            val concerts = listOf(
                ConcertItem("Guns And Roses LA", "LA Hall"),
                ConcertItem("Guns and Roses Denver", "Denver Hall"),
                ConcertItem("Guns and Roses New York", "Maddison Square Garden")
            )
            Column(modifier = Modifier.padding(8.dp)) {
                concerts.forEach { concert ->
                    ConcertListItem(concert)
                }
            }
        }
    }

    @Composable
    fun ConcertListItem(concert: ConcertItem) {
        ListItem(
            headlineContent = { Text(concert.title) },
            supportingContent = { Text(concert.location) },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
        )
    }

    data class ConcertItem(val title: String, val location: String)

    @Preview(showBackground = true)
    @Composable
    fun PreviewLugaresScreen() {
        TodoEventoappTheme {
            val navController = rememberNavController()
            PlacesScreen(navController)
        }
    }
