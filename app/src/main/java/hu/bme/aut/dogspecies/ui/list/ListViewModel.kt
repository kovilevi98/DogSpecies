package hu.bme.aut.dogspecies.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.dogspecies.model.Breed
import hu.bme.aut.dogspecies.repository.BreedRepository
import hu.bme.aut.dogspecies.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    var breedRepository: BreedRepository,
    var favoriteRepository: FavoriteRepository
) : ViewModel() {


    val breedList = mutableStateListOf<Breed>()
    val favoriteList = mutableStateListOf<Breed>()
    var actualList = mutableStateListOf<Breed>()

    var loading = mutableStateOf(false)
    var favorite = mutableStateOf(false)

    init {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val list = loadList()
            breedList.addAll(list)

            //favoriteRepository.insertFavorite(list.first())

            val favorites = loadFavorites()
            favoriteList.addAll(favorites)

            actualList.addAll(breedList)
            loading.value = false;
        }

    }


    suspend fun loadList(): List<Breed> {
        return  breedRepository.getBreeds()
    }

    suspend fun loadFavorites(): List<Breed> {
        return  favoriteRepository.getFavorites()
    }

    fun refresh() {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val list = loadList()
            breedList.addAll(list)
            loading.value = false
        }
    }

    fun selectFavorites(){
        actualList.clear()
        actualList.addAll(favoriteList)
    }

    fun selectAll(){
        actualList.clear()
        actualList.addAll(breedList)
    }

}
