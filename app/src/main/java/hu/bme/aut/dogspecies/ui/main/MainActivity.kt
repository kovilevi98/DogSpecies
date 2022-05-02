package hu.bme.aut.dogspecies.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.dogspecies.R
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.ui.list.ListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                AppBar()
                Column(
                    modifier = Modifier.fillMaxSize().background(color = Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (viewModel.loading.value) {
                        CircularProgressIndicator()
                    } else {
                        DogList(viewModel.breedList)
                    }
                }
            }
        }
        //setContentView(R.layout.activity_main)
    }

    @Composable
    fun DogList(breeds: List<Breed>?) {
        LazyColumn {
            if (breeds != null) {
                items(breeds.size) { breed ->
                    breeds[breed].name?.let { MessageCard(breeds[breed].name!!) }
                }
            }
        } }


    @Composable
    fun MessageCard(msg: String) {
        // Add padding around our message
        Card(
            modifier = Modifier.fillMaxWidth().background(color = Color.White),
            elevation = 4.dp
        ){
            Row(modifier = Modifier.padding(all = 8.dp).fillMaxWidth().background(color = Color.White)) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        // Set image size to 40 dp
                        .size(40.dp)
                        // Clip image to be shaped as a circle
                        .clip(CircleShape)
                )

                // Add a horizontal space between the image and the column
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(text = "msg.author")
                    // Add a vertical space between the author and message texts
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "sddsadsa")
                }
            }
        }
    }

    @Preview
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
                                    Log.e("refresh", "refresh")
                                    viewModel.refresh()
                                }) {
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
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "Favorite",
                                tint = Color.White,
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(modifier = Modifier.size(Dp(value = 10F)))
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Plus",
                                tint = Color.White,
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(modifier = Modifier.size(Dp(value = 10F)))
                        }
                    }
                },
                backgroundColor = Color.Blue,
            )
        }

    }

}