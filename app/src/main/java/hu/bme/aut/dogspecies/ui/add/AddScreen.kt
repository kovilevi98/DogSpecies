package hu.bme.aut.dogspecies.ui.add

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
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

class AddScreen(
    private val navController: NavHostController,
    private val detailsViewModel: DetailsViewModel,
) {
    @Composable
    fun Screen() {
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
                    style = TextStyle(
                        Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.size(Dp(value = 80F)))
                PhotoSelector()

            }
        }
    }

    @Composable
    fun PhotoSelector() {
        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract =
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            detailsViewModel.imageUri.value = uri
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (detailsViewModel.imageUri.value == null) {
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

            detailsViewModel.imageUri.value?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    detailsViewModel.bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    detailsViewModel.bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                detailsViewModel.bitmap.value?.let { btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp)
                    )
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