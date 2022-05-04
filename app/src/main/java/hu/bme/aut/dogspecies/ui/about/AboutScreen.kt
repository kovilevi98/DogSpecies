package hu.bme.aut.dogspecies.ui.about

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

class AboutScreen(
    private val navController: NavHostController,
) {

    @Composable
    fun Screen() {
        Log.e("about", "Screen")
        Column() {
            ReturnAppBar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "You can find the repository here:",
                    style = TextStyle(Color.White, textAlign = TextAlign.Center)
                )
                Text(
                    text = "https://github.com/kovilevi98/DogSpecies",
                    style = TextStyle(Color.White, textAlign = TextAlign.Center)
                )
            }
        }
    }

    @Composable
    fun ReturnAppBar() {
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
                                    navController.popBackStack()
                                }
                            ) {
                                Icon(
                                    Icons.Filled.ArrowBack,
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
                    }
                },
                backgroundColor = Color.Blue,
            )
        }

    }
}