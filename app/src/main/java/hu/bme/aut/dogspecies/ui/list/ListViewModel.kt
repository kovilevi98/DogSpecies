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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    var breedRepository: BreedRepository
) : ViewModel() {


    val breedList = mutableStateListOf<Breed>()
    val favoriteList = mutableStateListOf<Pair<Int, Boolean>>()

    var loading = mutableStateOf(false)

    init {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val list = loadList()
            breedList.addAll(list)
            breedList.forEach {
                favoriteList.add(Pair(it.id, false))
            }
            loading.value = false;
        }

    }


    suspend fun loadList(): List<Breed> {
        return  breedRepository.getBreeds()
    }

    fun refresh() {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val list = loadList()
            breedList.addAll(list)
            loading.value = false
        }
    }

}
