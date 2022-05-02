package hu.bme.aut.dogspecies.ui.main

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.glide.GlideImage
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.ui.details.DetailsViewModel
import hu.bme.aut.dogspecies.ui.list.ListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ListViewModel by viewModels()
    private lateinit var navController: NavHostController

    private val detailsViewModel: DetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }
        //setContentView(R.layout.activity_main)
    }

    @Composable
    fun Navigation() {
        navController = rememberNavController()
        NavHost(navController, startDestination = "list") {
            composable(route = "list") {
                ListScreen()
            }
            composable(route = "About") {
                AboutScreen()
            }
            composable(route = "Plus") {
                AddScreen()
            }
            /*composable(
                route = "Details/{dogId}",
                arguments = listOf(navArgument("dogId") { type = NavType.StringType })
            ) { backStackEntry ->
                AddScreen()
            }*/
        }
    }

    @Composable
    fun ListScreen() {
        Column() {
            AppBar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
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
    fun AboutScreen() {
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
    fun AddScreen() {
        Column() {
            ReturnAppBar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(Dp(value = 80F)))
                Text(
                    text = "Add new Dog",
                    style = TextStyle(Color.White, textAlign = TextAlign.Center, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.size(Dp(value = 80F)))
                PhotoSelector()

            }
        }
    }

    @Composable
    fun PhotoSelector() {
        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.GetContent()) { uri: Uri? ->
            detailsViewModel.imageUri.value = uri
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if(detailsViewModel.imageUri.value  == null){
                Row() {
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = {
                        launcher.launch("image/*")
                    }) {
                        Text(text = "Pick image")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            detailsViewModel.imageUri.value ?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    detailsViewModel.bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver,it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver,it)
                    detailsViewModel.bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                detailsViewModel.bitmap.value?.let {  btm ->
                    Image(bitmap = btm.asImageBitmap(),
                        contentDescription =null,
                        modifier = Modifier.size(180.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = detailsViewModel.name.value,
                onValueChange = {
                    detailsViewModel.name.value = it
                },
                label = { Text("Name") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = detailsViewModel.origin.value,
                onValueChange = {
                    detailsViewModel.origin.value = it
                },
                label = { Text("Origin") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = detailsViewModel.breedFor.value,
                onValueChange = {
                    detailsViewModel.breedFor.value = it
                },
                label = { Text("Breed For") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                //
            }) {
                Text(text = "Send")
            }
        }
    }

    @Composable
    fun DogList(breeds: List<Breed>?) {
        LazyColumn {
            if (breeds != null) {
                items(breeds.size) { breed ->
                    breeds[breed].name?.let {
                        MessageCard(
                            breeds[breed],
                            onClick = { navController.navigate("Details/${breeds[breed].id}") })
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun MessageCard(breed: Breed, onClick: () -> Unit) {
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
                            if(viewModel.favoriteList.contains(breed)){
                                Icon(
                                    Icons.Outlined.Star,
                                    "Info",
                                    tint = Color.Yellow,
                                    modifier = Modifier
                                )
                            } else {
                                Icon(
                                    Icons.Outlined.Star,
                                    "Info",
                                    tint = Color.Black,
                                    modifier = Modifier
                                )
                            }

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
                                    Log.e("refresh", "refresh")
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
                                    Log.e("refresh", "refresh")
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
                                    if(viewModel.favorite.value) {
                                        viewModel.selectFavorites()
                                    }
                                    else {
                                        viewModel.selectAll()
                                    }
                                }) {
                                if(viewModel.favorite.value){
                                    Icon(
                                        Icons.Filled.Star,
                                        "refresh",
                                        tint = Color.Yellow,
                                        modifier = Modifier
                                    )
                                } else Icon(
                                    Icons.Filled.Star,
                                    "refresh",
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
