package hu.bme.aut.dogspecies.ui.details

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.repository.BreedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    //var breedRepository: BreedRepository
) : ViewModel() {
    var imageUri = mutableStateOf<Uri?>(null)

    var bitmap = mutableStateOf<Bitmap?>(null)
    var name = mutableStateOf("")
    var origin = mutableStateOf("")
    var breedFor = mutableStateOf("")


    /*var breed = mutableStateOf<Breed?>(null)
    var loading = mutableStateOf(false)

    init {
    }


    private suspend fun getItem(id: Int): Breed? {
        return  breedRepository.getBreed(id)
    }

    fun refresh(id: Int) {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val result = getItem(id)
            breed.value = result
            loading.value = false
        }
    }*/

}