package kalbe.corp.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kalbe.corp.wishlistapp.data.Wish
import kalbe.corp.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository,

    ): ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newString: String){
        wishTitleState = newString
    }

    fun onWishDescriptionState(newDescription: String){
        wishDescriptionState = newDescription
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }
    }

    fun createWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.createWish(wish)
        }
    }

    fun editWish(wish: Wish){
        viewModelScope.launch {
            wishRepository.editWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish>{
        return wishRepository.getWishById(id)
    }
}