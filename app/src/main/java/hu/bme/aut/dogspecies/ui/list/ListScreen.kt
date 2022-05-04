package hu.bme.aut.dogspecies.ui.list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import hu.bme.aut.dogspecies.model.Breed

class ListScreen(
    private val navController: NavHostController,
    private val viewModel: ListViewModel
) {
    @Composable
    fun Screen() {
        Column() {
            AppBar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.loading.value) {
                    Loading()
                } else {
                    DogList(viewModel.actualList)
                }
            }
        }
    }


    @Composable
    fun DogList(breeds: List<Breed>?) {
        LazyColumn {
            if (breeds != null) {
                items(breeds.size) { breed ->
                    breeds[breed].name?.let {
                        DogCard(
                            breeds[breed],
                            onClick = { navController.navigate("Details/${breeds[breed].id}") })
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun DogCard(breed: Breed, onClick: () -> Unit) {
        // Add padding around our message
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray)
                .padding(all = 8.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Column {
                    Row(

                    ) {
                        breed.name?.let {
                            Text(
                                text = it,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        IconButton(
                            modifier = Modifier.then(Modifier.size(24.dp)),
                            onClick = {
                                //navController.popBackStack()
                            }
                        ) {
                            if (viewModel.favoriteList.contains(breed)) {
                                IconButton(
                                    modifier = Modifier.then(Modifier.size(24.dp)),
                                    onClick = {
                                        viewModel.removeFavorite(breed)
                                        viewModel.refreshFavoriteList()
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Star,
                                        "Info",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                    )
                                }
                            } else {
                                IconButton(
                                    modifier = Modifier.then(Modifier.size(24.dp)),
                                    onClick = {
                                        viewModel.addToFavorites(breed)
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Star,
                                        "Info",
                                        tint = Color.Black,
                                        modifier = Modifier
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            modifier = Modifier.then(Modifier.size(24.dp)),
                            onClick = {
                                navController.navigate("Plus")
                            }
                        ) {
                            Icon(
                                Icons.Filled.Edit,
                                "Edit",
                                tint = Color.Black,
                                modifier = Modifier
                            )
                        }
                    }
                    // Add a vertical space between the author and message texts
                    Spacer(modifier = Modifier.height(4.dp))
                    breed.breedFor?.let { Text(text = it) }

                    Spacer(modifier = Modifier.height(4.dp))
                    breed.origin?.let { Text(text = it) }
                }

                // Add a horizontal space between the image and the column
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(breed.image),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        // Set image size to 80 dp
                        .size(80.dp)
                )

            }
        }
    }

    @Composable
    fun Loading() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun AppBar() {
        Column {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row() {
                            IconButton(
                                modifier = Modifier.then(Modifier.size(24.dp)),
                                onClick = {
                                    navController.navigate("About")
                                }
                            ) {
                                Icon(
                                    Icons.Filled.Info,
                                    "Info",
                                    tint = Color.White,
                                    modifier = Modifier
                                )
                            }
                            Spacer(modifier = Modifier.size(Dp(value = 20F)))
                            Text(
                                text = "DogSpecies",
                                style = TextStyle(Color.White, textAlign = TextAlign.Center)
                            )

                        }
                        Row() {
                            IconButton(
                                modifier = Modifier.then(Modifier.size(24.dp)),
                                onClick = {
                                    viewModel.refresh()
                                }) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    "refresh",
                                    tint = Color.White,
                                    modifier = Modifier
                                )
                            }
                            Spacer(modifier = Modifier.size(Dp(value = 10F)))
                            IconButton(
                                modifier = Modifier.then(Modifier.size(24.dp)),
                                onClick = {
                                    viewModel.favorite.value = !viewModel.favorite.value
                                    if (viewModel.favorite.value) {
                                        viewModel.selectFavorites()
                                    } else {
                                        viewModel.selectAll()
                                    }
                                }) {
                                if (viewModel.favorite.value) {
                                    Icon(
                                        Icons.Filled.Star,
                                        "star",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                    )
                                } else Icon(
                                    Icons.Filled.Star,
                                    "star",
                                    tint = Color.White,
                                    modifier = Modifier
                                )
                            }
                            Spacer(modifier = Modifier.size(Dp(value = 10F)))
                            IconButton(
                                modifier = Modifier.then(Modifier.size(24.dp)),
                                onClick = {
                                    navController.navigate("Plus")
                                }) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Plus",
                                    tint = Color.White,
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                            }
                            Spacer(modifier = Modifier.size(Dp(value = 10F)))
                        }
                    }
                },
                backgroundColor = Color.Blue,
            )
        }

    }
}